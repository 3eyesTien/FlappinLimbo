package com.tiengame.flappinlimbo.com.tiengame.TweenAccessors;

import aurelienribon.tweenengine.TweenAccessor;

public class ValueAccessory implements TweenAccessor<Value> {

    @Override
    public int getValues(Value target, int tweenType, float[] returnValues)
    {
        returnValues[0] = target.getValue();
        return 1;
    }

    @Override
    public void setValues(Value target, int tweenType, float[] newValues)
    {
        target.setValue(newValues[0]);
    }
}
