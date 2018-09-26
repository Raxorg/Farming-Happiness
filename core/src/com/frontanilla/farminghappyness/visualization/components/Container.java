package com.frontanilla.farminghappyness.visualization.components;

public class Container extends Component {

    public Container(float x, float y, float width, float height) {
        super(x, y, width, height);
    }

    public float getWidth() {
        return bounds.width;
    }

    public float getHeight() {
        return bounds.height;
    }
}
