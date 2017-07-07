package yz.learn.basic;

/**
 * https://stackoverflow.com/questions/113511/best-implementation-for-hashcode-method
 */
public class HashCode {

    public static void main(String[] args) {
        A a = new A(1, (short) 1, (byte) 12, true, 1.2F, 1.2D, 1L, (char) 1);
        System.out.println(a.hashCode());
        System.out.println(a.HashCode());
    }

    private static class A{
        private int i;
        private short s;
        private byte by;
        private boolean bo;
        private float f;
        private double d;
        private long l;
        private char c;

        A(int i, short s, byte by, boolean bo, float f, double d, long l, char c) {
            this.i = i;
            this.s = s;
            this.by = by;
            this.bo = bo;
            this.f = f;
            this.d = d;
            this.l = l;
            this.c = c;
        }

        int HashCode(){
            int hash;
            hash = i;
            hash = hash * 31 + s;
            hash = hash * 31 + by;
            hash = hash * 31 + (bo ? 1 : 0);
            hash = hash * 31 + Float.floatToIntBits(f);
            long temp = Double.doubleToLongBits(d);
            hash = hash * 31 + (int)(temp ^ (temp >>> 32));
            hash = hash * 31 + (int)(l ^ (l >>> 32));
            hash = hash * 31 + c;
            return hash;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            A a = (A) o;

            if (i != a.i) return false;
            if (s != a.s) return false;
            if (by != a.by) return false;
            if (bo != a.bo) return false;
            if (Float.compare(a.f, f) != 0) return false;
            if (Double.compare(a.d, d) != 0) return false;
            if (l != a.l) return false;
            return c == a.c;
        }

        @Override
        public int hashCode() {
            int result;
            long temp;
            result = i;
            result = 31 * result + (int) s;
            result = 31 * result + (int) by;
            result = 31 * result + (bo ? 1 : 0);
            result = 31 * result + (f != +0.0f ? Float.floatToIntBits(f) : 0);
            temp = Double.doubleToLongBits(d);
            result = 31 * result + (int) (temp ^ (temp >>> 32));
            result = 31 * result + (int) (l ^ (l >>> 32));
            result = 31 * result + (int) c;
            return result;
        }
    }
}
