package ps.anu;

public class Range {
    private float low, high;

    public Range(float low, float high) {
        this.low = low;
        this.high = high;
    }

    float getLow() {
        return this.low;
    }

    float getHigh() {
        return this.high;
    }
}
