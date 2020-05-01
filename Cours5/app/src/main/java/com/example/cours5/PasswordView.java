package com.example.cours5;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.InputType;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

public class PasswordView extends View {
    private static final int numberOfInput = 4;
    private float widthInputZoneWeight = 1;
    private float widthMarginWeight = 0.5f;
    private float widthSumOfWeight;
    private float widthUnitWeight;

    private String correctPassword ="1234";
    private String currentEntry;

    private int viewHeight;
    private int viewWidth;

    private Paint inputBorderPaint;

    private OnGoodPasswordListener onGoodPasswordListener;
    private int mainColor;


    public PasswordView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setFocusable(true);
        setFocusableInTouchMode(true);
        widthSumOfWeight = numberOfInput * widthInputZoneWeight +(numberOfInput + 1) * widthMarginWeight;
        mainColor = ContextCompat.getColor(context,R.color.colorAccent);
        inputBorderPaint = new Paint();
        inputBorderPaint.setStyle(Paint.Style.STROKE);
        inputBorderPaint.setStrokeWidth(10);
        inputBorderPaint.setColor(mainColor);

        currentEntry = "";
    }

    @Override
    protected void onDraw(Canvas canvas){
        canvas.drawARGB(128,0,0,0);
        int marginSize = (int)(widthMarginWeight * widthUnitWeight);
        int boxSize = (int)(widthInputZoneWeight * widthUnitWeight);
        int current = marginSize;
        int topMargin = (viewHeight - boxSize)/2;

        for (int i= 0; i<numberOfInput;i++){
            RectF rectF = new RectF(current,topMargin,current + boxSize, topMargin + boxSize);
            canvas.drawRoundRect(rectF,5,5,inputBorderPaint);
            if(currentEntry.length()> i){
                canvas.drawCircle(current + boxSize /2, viewHeight /2, boxSize /2,inputBorderPaint);
            }
            current += boxSize + marginSize;
        }
    }
    @Override
    protected void onSizeChanged(int w, int h, int oldw,int oldh){
        viewHeight = h;
        viewWidth = w;
        widthUnitWeight = w/widthSumOfWeight;
        invalidate();
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec ){
        setMeasuredDimension(widthMeasureSpec,heightMeasureSpec/4);
    }
    @Override
    public InputConnection onCreateInputConnection(EditorInfo outAttrs){
        outAttrs.inputType = InputType.TYPE_CLASS_NUMBER;
        outAttrs.imeOptions = EditorInfo.IME_ACTION_DONE;
        return null;
    }

    public boolean click(){
        this.requestFocus();
        InputMethodManager inputMethodManager = (InputMethodManager)getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.showSoftInput(this,InputMethodManager.SHOW_FORCED);
        return performClick();

    }
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event){
        char unicodeChar = (char) event.getUnicodeChar();
        currentEntry += unicodeChar;
        if(currentEntry.length()>= numberOfInput){
            if(passwordIsValid()){
                Log.d("onKeyUp","Good Password");
                animateColor(Color.GREEN);
                if(onGoodPasswordListener!=null){
                    onGoodPasswordListener.onGoodPassword();
                }
            }else{
                animateColor(Color.RED);
                Log.d("onKeyUp","Wrong Password");
            }
            resetPassword();

        }
        invalidate();
        return true;
    }

    private void animateColor(int colorToAnimateToward){
        final ObjectAnimator colorFade = ObjectAnimator.ofObject(inputBorderPaint,"color",
                new ArgbEvaluator(),mainColor,colorToAnimateToward);
        colorFade.setDuration(1500);
        colorFade.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                invalidate();
            }
        });
        colorFade.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                inputBorderPaint.setColor(mainColor);
                invalidate();
            }
        });
        colorFade.start();
    }

    private void resetPassword(){
        currentEntry = "";
    }
    private boolean passwordIsValid(){
        return currentEntry.equals(correctPassword);
    }
    @Override
    public boolean onTouchEvent(final MotionEvent event){
        if (event.getAction() == MotionEvent.ACTION_UP){
            click();
        }
        return true;
    }
    public void setOnGoodPasswordListener(OnGoodPasswordListener newOnGoodPasswordListener){
        onGoodPasswordListener = newOnGoodPasswordListener;
    }
    public interface OnGoodPasswordListener{
        void onGoodPassword();
    }
}
