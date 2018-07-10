//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package hivatec.ir.hivatectools.hivaLoopView;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class LoopView extends View {
    
    
    private float scaleX = 1.05F;
    private static final int DEFAULT_TEXT_SIZE;
    private static final float DEFAULT_LINE_SPACE = 2.0F;
    private static final int DEFAULT_VISIBIE_ITEMS = 9;
    private Context context;
    Handler handler;
    private GestureDetector flingGestureDetector;
    OnItemSelectedListener onItemSelectedListener;
    ScheduledExecutorService mExecutor = Executors.newSingleThreadScheduledExecutor();
    private ScheduledFuture<?> mFuture;
    private Paint paintOuterText;
    private Paint paintCenterText;
    private Paint paintIndicator;
    List<String> items;
    int textSizeNotCenter;
    int textSizeCenter;
    int maxTextHeight;
    int outerTextColor;
    int centerTextColor;
    int dividerColor;
    float lineSpacingMultiplier;
    boolean isLoop;
    int firstLineY;
    int secondLineY;
    int totalScrollY;
    int initPosition;
    private int selectedItem;
    int preCurrentIndex;
    int change;
    int itemsVisibleCount;
    String[] drawingStrings;
    int measuredHeight;
    int measuredWidth;
    int halfCircumference;
    int radius;
    private int mOffset = 0;
    private float previousY;
    long startTime = 0L;
    private Rect tempRect = new Rect();
    private int paddingLeft;
    private int paddingRight;

    public void setLineSpacingMultiplier(float lineSpacingMultiplier) {
        if(lineSpacingMultiplier > 1.0F) {
            this.lineSpacingMultiplier = lineSpacingMultiplier;
        }

    }

    public void setCenterTextColor(int centerTextColor) {
        this.centerTextColor = centerTextColor;
        this.paintCenterText.setColor(centerTextColor);
    }

    public void setOuterTextColor(int outerTextColor) {
        this.outerTextColor = outerTextColor;
        this.paintOuterText.setColor(outerTextColor);
    }

    public void setDividerColor(int dividerColor) {
        this.dividerColor = dividerColor;
        this.paintIndicator.setColor(dividerColor);
    }

    public LoopView(Context context) {
        super(context);
        this.initLoopView(context, (AttributeSet)null);
    }

    public LoopView(Context context, AttributeSet attributeset) {
        super(context, attributeset);
        this.initLoopView(context, attributeset);
    }

    public LoopView(Context context, AttributeSet attributeset, int defStyleAttr) {
        super(context, attributeset, defStyleAttr);
        this.initLoopView(context, attributeset);
    }

    private void initLoopView(Context context, AttributeSet attributeset) {
        this.context = context;
        this.handler = new MessageHandler(this);
        this.flingGestureDetector = new GestureDetector(context, new LoopViewGestureListener(this));
        this.flingGestureDetector.setIsLongpressEnabled(false);
        TypedArray typedArray = context.obtainStyledAttributes(attributeset, com.weigan.loopview.R.styleable.androidWheelView);
        this.textSizeNotCenter = typedArray.getInteger(com.weigan.loopview.R.styleable.androidWheelView_awv_textsize, DEFAULT_TEXT_SIZE);
        this.textSizeNotCenter = (int)(Resources.getSystem().getDisplayMetrics().density * (float)this.textSizeNotCenter);
        this.lineSpacingMultiplier = typedArray.getFloat(com.weigan.loopview.R.styleable.androidWheelView_awv_lineSpace, 2.0F);
        this.centerTextColor = typedArray.getInteger(com.weigan.loopview.R.styleable.androidWheelView_awv_centerTextColor, -13553359);
        this.outerTextColor = typedArray.getInteger(com.weigan.loopview.R.styleable.androidWheelView_awv_outerTextColor, -5263441);
        this.dividerColor = typedArray.getInteger(com.weigan.loopview.R.styleable.androidWheelView_awv_dividerTextColor, -3815995);
        this.itemsVisibleCount = typedArray.getInteger(com.weigan.loopview.R.styleable.androidWheelView_awv_itemsVisibleCount, 9);
        if(this.itemsVisibleCount % 2 == 0) {
            this.itemsVisibleCount = 9;
        }

        this.isLoop = typedArray.getBoolean(com.weigan.loopview.R.styleable.androidWheelView_awv_isLoop, true);
        typedArray.recycle();
        this.drawingStrings = new String[this.itemsVisibleCount];
        this.totalScrollY = 0;
        this.initPosition = -1;
        this.initPaints();
    }


    public void setItemsVisibleCount(int visibleNumber) {
        if(visibleNumber % 2 != 0) {
            if(visibleNumber != this.itemsVisibleCount) {
                this.itemsVisibleCount = visibleNumber;
                this.drawingStrings = new String[this.itemsVisibleCount];
            }

        }
    }

    private void initPaints() {

        Typeface mainFontTf = Typeface.createFromAsset(context.getAssets(), "IRANSansMobile.ttf");
        this.paintOuterText = new Paint();
        this.paintOuterText.setColor(this.outerTextColor);
        this.paintOuterText.setAntiAlias(true);
        this.paintOuterText.setTypeface(mainFontTf);
        this.paintOuterText.setTextSize((float)this.textSizeNotCenter);
        this.paintCenterText = new Paint();
        this.paintCenterText.setColor(this.centerTextColor);
        this.paintCenterText.setAntiAlias(true);
        this.paintCenterText.setTextScaleX(this.scaleX);
        this.paintCenterText.setTypeface(mainFontTf);
        this.paintCenterText.setTextSize((float)this.textSizeCenter);
        this.paintIndicator = new Paint();
        this.paintIndicator.setColor(this.dividerColor);
        this.paintIndicator.setAntiAlias(true);

    }

    private void remeasure() {
        if(this.items != null) {
            this.measuredWidth = this.getMeasuredWidth();
            this.measuredHeight = this.getMeasuredHeight();
            if(this.measuredWidth != 0 && this.measuredHeight != 0) {
                this.paddingLeft = this.getPaddingLeft();
                this.paddingRight = this.getPaddingRight();
                this.measuredWidth -= this.paddingRight;
                this.paintCenterText.getTextBounds("星期", 0, 2, this.tempRect);
                this.maxTextHeight = this.tempRect.height();
                this.halfCircumference = (int)((double)this.measuredHeight * 3.141592653589793D / 2.0D);
                this.maxTextHeight = (int)((float)this.halfCircumference / (this.lineSpacingMultiplier * (float)(this.itemsVisibleCount - 1)));
                this.radius = this.measuredHeight / 2;
                this.firstLineY = (int)(((float)this.measuredHeight - this.lineSpacingMultiplier * (float)this.maxTextHeight) / 2.0F);
                this.secondLineY = (int)(((float)this.measuredHeight + this.lineSpacingMultiplier * (float)this.maxTextHeight) / 2.0F);
                if(this.initPosition == -1) {
                    if(this.isLoop) {
                        //this.initPosition = (this.items.size() + 1) / 2;
                        this.initPosition = 0;
                    } else {
                        this.initPosition = 0;
                    }
                }

                this.preCurrentIndex = this.initPosition;
            }
        }
    }

    void smoothScroll(LoopView.ACTION action) {
        this.cancelFuture();
        if(action == LoopView.ACTION.FLING || action == LoopView.ACTION.DAGGLE) {
            float itemHeight = this.lineSpacingMultiplier * (float)this.maxTextHeight;
            this.mOffset = (int)(((float)this.totalScrollY % itemHeight + itemHeight) % itemHeight);
            if((float)this.mOffset > itemHeight / 2.0F) {
                this.mOffset = (int)(itemHeight - (float)this.mOffset);
            } else {
                this.mOffset = -this.mOffset;
            }
        }

        this.mFuture = this.mExecutor.scheduleWithFixedDelay(new SmoothScrollTimerTask(this, this.mOffset), 0L, 10L, TimeUnit.MILLISECONDS);
    }

    protected final void scrollBy(float velocityY) {
        this.cancelFuture();
        byte velocityFling = 10;
        this.mFuture = this.mExecutor.scheduleWithFixedDelay(new InertiaTimerTask(this, velocityY), 0L, (long)velocityFling, TimeUnit.MILLISECONDS);
    }

    public void cancelFuture() {
        if(this.mFuture != null && !this.mFuture.isCancelled()) {
            this.mFuture.cancel(true);
            this.mFuture = null;
        }

    }

    public void setNotLoop() {
        this.isLoop = false;
    }

    public final void setTextSizeNotCenter(float size) {
        if(size > 0.0F) {
            this.textSizeNotCenter = (int)(this.context.getResources().getDisplayMetrics().density * size);
            this.paintOuterText.setTextSize((float)this.textSizeNotCenter);

        }

    }
    public final void setTextSizeCenter(float size) {
        if(size > 0.0F) {
            this.textSizeCenter = (int)(this.context.getResources().getDisplayMetrics().density * size);
            this.paintCenterText.setTextSize((float)this.textSizeCenter);
        }

    }

    public final void setInitPosition(int initPosition) {
        if(initPosition < 0) {
            this.initPosition = 0;
        } else if(this.items != null && this.items.size() > initPosition) {
            this.initPosition = initPosition;
        }

    }

    public final void setListener(OnItemSelectedListener OnItemSelectedListener) {
        this.onItemSelectedListener = OnItemSelectedListener;
    }

    public final void setItems(List<String> items) {
        this.items = items;
        this.remeasure();
        this.invalidate();
    }

    public final int getSelectedItem() {
        return this.selectedItem;
    }

    protected final void onItemSelected() {
        if(this.onItemSelectedListener != null) {
            this.postDelayed(new OnItemSelectedRunnable(this), 200L);
        }

    }

    public void setScaleX(float scaleX) {
        this.scaleX = scaleX;
    }

    public void setCurrentPosition(int position) {
        if(this.items != null && !this.items.isEmpty()) {
            int size = this.items.size();
            if(position >= 0 && position < size && position != this.selectedItem) {
                this.initPosition = position;
                this.totalScrollY = 0;
                this.mOffset = 0;
                this.invalidate();
            }

        }
    }

    protected void onDraw(Canvas canvas) {
        if(this.items != null) {
            this.change = (int)((float)this.totalScrollY / (this.lineSpacingMultiplier * (float)this.maxTextHeight));
            this.preCurrentIndex = this.initPosition + this.change % this.items.size();
            if(!this.isLoop) {
                if(this.preCurrentIndex < 0) {
                    this.preCurrentIndex = 0;
                }

                if(this.preCurrentIndex > this.items.size() - 1) {
                    this.preCurrentIndex = this.items.size() - 1;
                }
            } else {
                if(this.preCurrentIndex < 0) {
                    this.preCurrentIndex += this.items.size();
                }

                if(this.preCurrentIndex > this.items.size() - 1) {
                    this.preCurrentIndex -= this.items.size();
                }
            }

            int j2 = (int)((float)this.totalScrollY % (this.lineSpacingMultiplier * (float)this.maxTextHeight));

            int i;
            for(int k1 = 0; k1 < this.itemsVisibleCount; ++k1) {
                i = this.preCurrentIndex - (this.itemsVisibleCount / 2 - k1);
                if(!this.isLoop) {
                    if(i < 0) {
                        this.drawingStrings[k1] = "";
                    } else if(i > this.items.size() - 1) {
                        this.drawingStrings[k1] = "";
                    } else {
                        this.drawingStrings[k1] = (String)this.items.get(i);
                    }
                } else {
                    while(i < 0) {
                        i += this.items.size();
                    }

                    while(i > this.items.size() - 1) {
                        i -= this.items.size();
                    }

                    this.drawingStrings[k1] = (String)this.items.get(i);
                }
            }

            canvas.drawLine((float)this.paddingLeft, (float)this.firstLineY, (float)this.measuredWidth, (float)this.firstLineY, this.paintIndicator);
            canvas.drawLine((float)this.paddingLeft, (float)this.secondLineY, (float)this.measuredWidth, (float)this.secondLineY, this.paintIndicator);

            for(i = 0; i < this.itemsVisibleCount; ++i) {
                canvas.save();
                float itemHeight = (float)this.maxTextHeight * this.lineSpacingMultiplier;
                double radian = (double)(itemHeight * (float)i - (float)j2) * 3.141592653589793D / (double)this.halfCircumference;
                if(radian < 3.141592653589793D && radian > 0.0D) {
                    int translateY = (int)((double)this.radius - Math.cos(radian) * (double)this.radius - Math.sin(radian) * (double)this.maxTextHeight / 2.0D);
                    canvas.translate(0.0F, (float)translateY);
                    canvas.scale(1.0F, (float) Math.sin(radian));
                    if(translateY <= this.firstLineY && this.maxTextHeight + translateY >= this.firstLineY) {
                        canvas.save();
                        canvas.clipRect(0, 0, this.measuredWidth, this.firstLineY - translateY);
                        canvas.drawText(this.drawingStrings[i], (float)this.getTextX(this.drawingStrings[i], this.paintOuterText, this.tempRect), (float)this.maxTextHeight, this.paintOuterText);
                        canvas.restore();
                        canvas.save();
                        canvas.clipRect(0, this.firstLineY - translateY, this.measuredWidth, (int)itemHeight);
                        canvas.drawText(this.drawingStrings[i], (float)this.getTextX(this.drawingStrings[i], this.paintCenterText, this.tempRect), (float)this.maxTextHeight, this.paintCenterText);
                        canvas.restore();
                    } else if(translateY <= this.secondLineY && this.maxTextHeight + translateY >= this.secondLineY) {
                        canvas.save();
                        canvas.clipRect(0, 0, this.measuredWidth, this.secondLineY - translateY);
                        canvas.drawText(this.drawingStrings[i], (float)this.getTextX(this.drawingStrings[i], this.paintCenterText, this.tempRect), (float)this.maxTextHeight , this.paintCenterText);
                        canvas.restore();
                        canvas.save();
                        canvas.clipRect(0, this.secondLineY - translateY, this.measuredWidth, (int)itemHeight);
                        canvas.drawText(this.drawingStrings[i], (float)this.getTextX(this.drawingStrings[i], this.paintOuterText, this.tempRect), (float)this.maxTextHeight, this.paintOuterText);
                        canvas.restore();
                    } else if(translateY >= this.firstLineY && this.maxTextHeight + translateY <= this.secondLineY) {
                        canvas.clipRect(0, 0, this.measuredWidth, (int)itemHeight);
                        canvas.drawText(this.drawingStrings[i], (float)this.getTextX(this.drawingStrings[i], this.paintCenterText, this.tempRect), (float)this.maxTextHeight, this.paintCenterText);
                        this.selectedItem = this.items.indexOf(this.drawingStrings[i]);
                    } else {
                        canvas.clipRect(0, 0, this.measuredWidth, (int)itemHeight);
                        canvas.drawText(this.drawingStrings[i], (float)this.getTextX(this.drawingStrings[i], this.paintOuterText, this.tempRect), (float)this.maxTextHeight, this.paintOuterText);
                    }

                    canvas.restore();
                } else {
                    canvas.restore();
                }
            }

        }
    }

    private int getTextX(String a, Paint paint, Rect rect) {
        paint.getTextBounds(a, 0, a.length(), rect);
        int textWidth = rect.width();
        textWidth = (int)((float)textWidth * this.scaleX);
        return (this.measuredWidth - this.paddingLeft - textWidth) / 2 + this.paddingLeft;
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        this.remeasure();
    }

    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    public boolean onTouchEvent(MotionEvent event) {
        boolean eventConsumed = this.flingGestureDetector.onTouchEvent(event);
        float itemHeight = this.lineSpacingMultiplier * (float)this.maxTextHeight;
        float y;
        switch(event.getAction()) {
            case 0:
                this.startTime = System.currentTimeMillis();
                this.cancelFuture();
                this.previousY = event.getRawY();
                if(this.getParent() != null) {
                    this.getParent().requestDisallowInterceptTouchEvent(true);
                }
                break;
            case 1:
            case 3:
            default:
                if(!eventConsumed) {
                    y = event.getY();
                    double l1 = Math.acos((double)(((float)this.radius - y) / (float)this.radius)) * (double)this.radius;
                    int circlePosition = (int)((l1 + (double)(itemHeight / 2.0F)) / (double)itemHeight);
                    float extraOffset = ((float)this.totalScrollY % itemHeight + itemHeight) % itemHeight;
                    this.mOffset = (int)((float)(circlePosition - this.itemsVisibleCount / 2) * itemHeight - extraOffset);
                    if(System.currentTimeMillis() - this.startTime > 120L) {
                        this.smoothScroll(LoopView.ACTION.DAGGLE);
                    } else {
                        this.smoothScroll(LoopView.ACTION.CLICK);
                    }
                }

                if(this.getParent() != null) {
                    this.getParent().requestDisallowInterceptTouchEvent(false);
                }
                break;
            case 2:
                float dy = this.previousY - event.getRawY();
                this.previousY = event.getRawY();
                this.totalScrollY = (int)((float)this.totalScrollY + dy);
                if(!this.isLoop) {
                    y = (float)(-this.initPosition) * itemHeight;
                    float l = (float)(this.items.size() - 1 - this.initPosition) * itemHeight;
                    if((float)this.totalScrollY < y) {
                        this.totalScrollY = (int)y;
                    } else if((float)this.totalScrollY > l) {
                        this.totalScrollY = (int)l;
                    }
                }
        }

        this.invalidate();
        return true;
    }

    static {
        DEFAULT_TEXT_SIZE = (int)(Resources.getSystem().getDisplayMetrics().density * 15.0F);
    }

    public static enum ACTION {
        CLICK,
        FLING,
        DAGGLE;

        private ACTION() {
        }
    }

    final class InertiaTimerTask implements Runnable {
        float a;
        final float velocityY;
        final LoopView loopView;

        InertiaTimerTask(LoopView loopview, float velocityY) {
            this.loopView = loopview;
            this.velocityY = velocityY;
            this.a = 2.14748365E9F;
        }

        public final void run() {
            if(this.a == 2.14748365E9F) {
                if(Math.abs(this.velocityY) > 2000.0F) {
                    if(this.velocityY > 0.0F) {
                        this.a = 2000.0F;
                    } else {
                        this.a = -2000.0F;
                    }
                } else {
                    this.a = this.velocityY;
                }
            }

            if(Math.abs(this.a) >= 0.0F && Math.abs(this.a) <= 20.0F) {
                this.loopView.cancelFuture();
                this.loopView.handler.sendEmptyMessage(2000);
            } else {
                int i = (int)(this.a * 10.0F / 1000.0F);
                LoopView loopview = this.loopView;
                loopview.totalScrollY -= i;
                if(!this.loopView.isLoop) {
                    float itemHeight = this.loopView.lineSpacingMultiplier * (float)this.loopView.maxTextHeight;
                    if(this.loopView.totalScrollY <= (int)((float)(-this.loopView.initPosition) * itemHeight)) {
                        this.a = 40.0F;
                        this.loopView.totalScrollY = (int)((float)(-this.loopView.initPosition) * itemHeight);
                    } else if(this.loopView.totalScrollY >= (int)((float)(this.loopView.items.size() - 1 - this.loopView.initPosition) * itemHeight)) {
                        this.loopView.totalScrollY = (int)((float)(this.loopView.items.size() - 1 - this.loopView.initPosition) * itemHeight);
                        this.a = -40.0F;
                    }
                }

                if(this.a < 0.0F) {
                    this.a += 20.0F;
                } else {
                    this.a -= 20.0F;
                }

                this.loopView.handler.sendEmptyMessage(1000);
            }
        }
    }

    final class LoopViewGestureListener extends GestureDetector.SimpleOnGestureListener {
        final LoopView loopView;

        LoopViewGestureListener(LoopView loopview) {
            this.loopView = loopview;
        }

        public final boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            this.loopView.scrollBy(velocityY);
            return true;
        }
    }


    final class MessageHandler extends Handler {
        public static final int WHAT_INVALIDATE_LOOP_VIEW = 1000;
        public static final int WHAT_SMOOTH_SCROLL = 2000;
        public static final int WHAT_ITEM_SELECTED = 3000;
        final LoopView loopview;

        MessageHandler(LoopView loopview) {
            this.loopview = loopview;
        }

        public final void handleMessage(Message msg) {
            switch(msg.what) {
                case 1000:
                    this.loopview.invalidate();
                    break;
                case 2000:
                    this.loopview.smoothScroll(LoopView.ACTION.FLING);
                    break;
                case 3000:
                    this.loopview.onItemSelected();
            }

        }
    }

    final class OnItemSelectedRunnable implements Runnable {
        final LoopView loopView;

        OnItemSelectedRunnable(LoopView loopview) {
            this.loopView = loopview;
        }

        public final void run() {
            this.loopView.onItemSelectedListener.onItemSelected(this.loopView.getSelectedItem());
        }
    }


    final class SmoothScrollTimerTask implements Runnable {
        int realTotalOffset;
        int realOffset;
        int offset;
        final LoopView loopView;

        SmoothScrollTimerTask(LoopView loopview, int offset) {
            this.loopView = loopview;
            this.offset = offset;
            this.realTotalOffset = 2147483647;
            this.realOffset = 0;
        }

        public final void run() {
            if(this.realTotalOffset == 2147483647) {
                this.realTotalOffset = this.offset;
            }

            this.realOffset = (int)((float)this.realTotalOffset * 0.1F);
            if(this.realOffset == 0) {
                if(this.realTotalOffset < 0) {
                    this.realOffset = -1;
                } else {
                    this.realOffset = 1;
                }
            }

            if(Math.abs(this.realTotalOffset) <= 0) {
                this.loopView.cancelFuture();
                this.loopView.handler.sendEmptyMessage(3000);
            } else {
                this.loopView.totalScrollY += this.realOffset;
                this.loopView.handler.sendEmptyMessage(1000);
                this.realTotalOffset -= this.realOffset;
            }

        }
    }

    public interface OnItemSelectedListener {
        void onItemSelected(int var1);
    }
}
