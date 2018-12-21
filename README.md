# HivaTecTools


HivaButton
-----
simple button
```XML
<hivatec.ir.hivatectools.views.HivaButton

  android:id="@+id/button"
  android:layout_width="wrap_content"
  android:layout_height="wrap_content"

  app:text="hello"
  app:textColor="#ffffff"
  app:typeface="IRANSansMobile.ttf"

  app:backgroundColor="@color/Purple"

  app:heightPadding="10dp"
  app:widthPadding="50dp"

  app:icon="@drawable/ic_check"
  app:iconPosition="right"

  app:radius="5dp"
/>
```

#### properties

* title
* textColor
* textSize
* space [space between icon and title]
* rippleColor [defualt is textColor]
* widthPadding
* heightPadding

icon
* icon
* iconWidth
* iconTint [default is textColor]


#### shape

custom
* backgroundDrawable [base drawable]

solid
* backgroundColor

stroke
* strokeColor
* strokeWidth
* strokeDashGap [defualt is solid] [----] [- - -]  [-  -  -]
* strokePressedColor

gradient
* backgroundColor [start color]
* backgroundSecondColor [end color]
* gradientAngle [default is 90(top->bottom)]

corner
* radius [all]
* topLeftCorner
* bottomLeftCorner
* topRightCorner
* bottomRightCorner
[setting any corner will replace radius on that corner only]
* rounded

#### disabled
* disabledDrawable [custom drawable]
* disabledBackground
* disabledForeground

#### toggle button

* isToggle 
* isOn 
* toggleGroup [if a group of toggle buttons are inside the same parent, and only one can be selected, toggleGroup should be the same for all of them]
* toggleGroupCanBeEmpty [if set false, one of the buttons should be on at all time]

toggle off state
* titleOff 
* iconOff
* textColorOff
* backgroundColorOff
* backgroundDrawableOff
* backgroundSecondColorOff
* gradientAngleOff
* rippleColorOff
* iconTintOff
* strokeColorOff
* strokeDashGapOff
* strokePressedColorOff



