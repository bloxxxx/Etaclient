package io.github.bloxxxx.etaclient.menu.widget;

public class WidgetAnimationTimer {

    private float value;
    private float target;
    private float time;
    private float speed;
    private float start;
    private final EasingType easingType;
    public WidgetAnimationTimer(EasingType easingType, float value) {
        this.value = value;
        target = value;
        start = value;
        time = 0;
        speed = 1;
        this.easingType = easingType;
    }

    public WidgetAnimationTimer(EasingType easingType) {
        this(easingType, 0);
    }
    public WidgetAnimationTimer(EasingType easingType, boolean value) {
        this(easingType, value ? 1 : 0);
    }

    public void update(float dt) {
        if (value != target) time = Math.min(1, time + speed * dt);
        switch (easingType) {
            case LINEAR -> updateLinear();
            case QUAD_IN -> updateQuadIn();
            case QUAD_OUT -> updateQuadOut();
            case CUBIC_IN -> updateCubicIn();
            case CUBIC_OUT -> updateCubicOut();
            case SMOOTH_STEP -> updateSmoothStep();
            case LERP -> updateLerp(dt);
        }
        value = Math.clamp(value, 0, 1);
    }

    private void updateLinear() {
        value = interpolate(time);
    }

    private void updateQuadIn() {
        value = interpolate(time * time);
    }

    private void updateQuadOut() {
        value = interpolate(1 - (1 - time) * (1 - time));
    }

    private void updateCubicIn() {
        value = interpolate(time * time * time);
    }

    private void updateCubicOut() {
        value = interpolate(1 - (1 - time) * (1 - time) * (1 - time));
    }

    private void updateSmoothStep() {
        value = interpolate(time * time * (3 - 2 * time));
    }

    private float interpolate(float time) {
        return start + (target - start) * time;
    }

    private void updateLerp(float dt) {
        value += (target - value) * dt * speed;
    }

    public void setTarget(float target, float speed) {
        this.time = 0;
        this.start = this.value;
        this.target = target;
        this.speed = speed;
    }

    public void setTarget(boolean target, float speed) {
        setTarget(target ? 1 : 0, speed);
    }

    public void setValue(float value) {
        this.value = value;
        this.target = value;
        this.speed = 0;
    }
    public void setValue(boolean value) {
        setValue(value ? 1 : 0);
    }

    public float getValue() {
        return value;
    }

    public float targetDist() {
        return Math.abs(target - value);
    }

    public enum EasingType {
        LINEAR,
        QUAD_IN,
        QUAD_OUT,
        CUBIC_IN,
        CUBIC_OUT,
        SMOOTH_STEP,
        LERP
    }
}
