package humans;

import lombok.Data;

import java.util.Objects;

@Data
public class Coordinates {
    private Long x;
    private float y;

    public Long getX() {
        return x;
    }

    public Coordinates(Long x, float y) {
        this.x = x;
        this.y = y;
    }

    public Coordinates() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates that = (Coordinates) o;
        return Float.compare(that.y, y) == 0 && Objects.equals(x, that.x);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
