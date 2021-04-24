package com.bariumhoof.bgfx4j.layout;

import lombok.Value;
import org.jetbrains.annotations.NotNull;

public abstract class Vertex {

    public static <T1 extends Vec<?, ?>> Vertex1<T1> vertex(T1 t1) {
        return Vertex1.of(t1);
    }

    public static <T1 extends Vec<?, ?>, T2 extends Vec<?, ?>> Vertex2<T1, T2> vertex(T1 t1, T2 t2) {
        return Vertex2.of(t1, t2);
    }

    public static <T1 extends Vec<?, ?>, T2 extends Vec<?, ?>, T3 extends Vec<?, ?>> Vertex3<T1, T2, T3> vertex(T1 t1, T2 t2, T3 t3) {
        return Vertex3.of(t1, t2, t3);
    }

    public static <T1 extends Vec<?, ?>, T2 extends Vec<?, ?>, T3 extends Vec<?, ?>, T4 extends Vec<?, ?>> Vertex4<T1, T2, T3, T4> vertex(T1 t1, T2 t2, T3 t3, T4 t4) {
        return Vertex4.of(t1, t2, t3, t4);
    }

    public abstract Vec<?, ?>[] array();

    public abstract int size();

    @Value(staticConstructor = "of")
    public static class Vertex1<T1 extends Vec<?, ?>> extends Vertex {
        @NotNull T1 t1;

        @Override
        public int size() {
            return 1;
        }

        @Override
        public Vec<?, ?>[] array() {
            return new Vec<?, ?>[]{t1};
        }
    }

    @Value(staticConstructor = "of")
    public static class Vertex2<T1 extends Vec<?, ?>, T2 extends Vec<?, ?>> extends Vertex {
        @NotNull T1 t1;
        @NotNull T2 t2;

        @Override
        public int size() {
            return 2;
        }

        @Override
        public Vec<?, ?>[] array() {
            return new Vec<?, ?>[]{t1, t2};
        }
    }

    public static class Vertex3<T1 extends Vec<?, ?>, T2 extends Vec<?, ?>, T3 extends Vec<?, ?>> extends Vertex {
        @NotNull
        private final T1 t1;
        @NotNull
        private final T2 t2;
        @NotNull
        private final T3 t3;

        public Vertex3(@NotNull T1 t1, @NotNull T2 t2, @NotNull T3 t3) {
            this.t1 = t1;
            this.t2 = t2;
            this.t3 = t3;
        }

        public static <T1 extends Vec<?, ?>, T2 extends Vec<?, ?>, T3 extends Vec<?, ?>> Vertex3<T1, T2, T3> of(@NotNull T1 t1, @NotNull T2 t2, @NotNull T3 t3) {
            return new Vertex3<T1, T2, T3>(t1, t2, t3);
        }

        @Override
        public int size() {
            return 3;
        }

        @Override
        public Vec<?, ?>[] array() {
            return new Vec<?, ?>[]{t1, t2, t3};
        }

        public @NotNull T1 getT1() {
            return this.t1;
        }

        public @NotNull T2 getT2() {
            return this.t2;
        }

        public @NotNull T3 getT3() {
            return this.t3;
        }

        public boolean equals(final Object o) {
            if (o == this) return true;
            if (!(o instanceof Vertex3)) return false;
            final Vertex3<?, ?, ?> other = (Vertex3<?, ?, ?>) o;
            if (!other.canEqual((Object) this)) return false;
            final Object this$t1 = this.getT1();
            final Object other$t1 = other.getT1();
            if (this$t1 == null ? other$t1 != null : !this$t1.equals(other$t1)) return false;
            final Object this$t2 = this.getT2();
            final Object other$t2 = other.getT2();
            if (this$t2 == null ? other$t2 != null : !this$t2.equals(other$t2)) return false;
            final Object this$t3 = this.getT3();
            final Object other$t3 = other.getT3();
            if (this$t3 == null ? other$t3 != null : !this$t3.equals(other$t3)) return false;
            return true;
        }

        protected boolean canEqual(final Object other) {
            return other instanceof Vertex3;
        }

        public int hashCode() {
            final int PRIME = 59;
            int result = 1;
            final Object $t1 = this.getT1();
            result = result * PRIME + ($t1 == null ? 43 : $t1.hashCode());
            final Object $t2 = this.getT2();
            result = result * PRIME + ($t2 == null ? 43 : $t2.hashCode());
            final Object $t3 = this.getT3();
            result = result * PRIME + ($t3 == null ? 43 : $t3.hashCode());
            return result;
        }

        public String toString() {
            return "Vertex.Vertex3(t1=" + this.getT1() + ", t2=" + this.getT2() + ", t3=" + this.getT3() + ")";
        }
    }

    @Value(staticConstructor = "of")
    public static class Vertex4<T1 extends Vec<?, ?>, T2 extends Vec<?, ?>, T3 extends Vec<?, ?>, T4 extends Vec<?, ?>> extends Vertex {
        @NotNull T1 t1;
        @NotNull T2 t2;
        @NotNull T3 t3;
        @NotNull T4 t4;

        @Override
        public int size() {
            return 4;
        }

        @Override
        public Vec<?, ?>[] array() {
            return new Vec<?, ?>[]{t1, t2, t3, t4};
        }
    }

    @Value(staticConstructor = "of")
    public static class Vertex5<T1 extends Vec<?, ?>, T2 extends Vec<?, ?>, T3 extends Vec<?, ?>, T4 extends Vec<?, ?>, T5 extends Vec<?, ?>> extends Vertex {
        @NotNull T1 t1;
        @NotNull T2 t2;
        @NotNull T3 t3;
        @NotNull T4 t4;
        @NotNull T5 t5;

        @Override
        public int size() {
            return 5;
        }

        @Override
        public Vec<?, ?>[] array() {
            return new Vec<?, ?>[]{t1, t2, t3, t4, t5};
        }
    }

    @Value(staticConstructor = "of")
    public static class Vertex6<T1 extends Vec<?, ?>, T2 extends Vec<?, ?>, T3 extends Vec<?, ?>, T4 extends Vec<?, ?>, T5 extends Vec<?, ?>, T6 extends Vec<?, ?>> extends Vertex {
        @NotNull T1 t1;
        @NotNull T2 t2;
        @NotNull T3 t3;
        @NotNull T4 t4;
        @NotNull T5 t5;
        @NotNull T6 t6;

        @Override
        public int size() {
            return 6;
        }

        @Override
        public Vec<?, ?>[] array() {
            return new Vec<?, ?>[]{t1, t2, t3, t4, t5, t6};
        }
    }

    @Value(staticConstructor = "of")
    public static class Vertex7<T1 extends Vec<?, ?>, T2 extends Vec<?, ?>, T3 extends Vec<?, ?>, T4 extends Vec<?, ?>, T5 extends Vec<?, ?>, T6 extends Vec<?, ?>, T7 extends Vec<?, ?>> extends Vertex {
        @NotNull T1 t1;
        @NotNull T2 t2;
        @NotNull T3 t3;
        @NotNull T4 t4;
        @NotNull T5 t5;
        @NotNull T6 t6;
        @NotNull T7 t7;

        @Override
        public int size() {
            return 7;
        }

        @Override
        public Vec<?, ?>[] array() {
            return new Vec<?, ?>[]{t1, t2, t3, t4, t5, t6, t7};
        }
    }

    @Value(staticConstructor = "of")
    public static class Vertex8<T1 extends Vec<?, ?>, T2 extends Vec<?, ?>, T3 extends Vec<?, ?>, T4 extends Vec<?, ?>, T5 extends Vec<?, ?>, T6 extends Vec<?, ?>, T7 extends Vec<?, ?>, T8 extends Vec<?, ?>> extends Vertex {
        @NotNull T1 t1;
        @NotNull T2 t2;
        @NotNull T3 t3;
        @NotNull T4 t4;
        @NotNull T5 t5;
        @NotNull T6 t6;
        @NotNull T7 t7;
        @NotNull T8 t8;

        @Override
        public int size() {
            return 8;
        }

        @Override
        public Vec<?, ?>[] array() {
            return new Vec<?, ?>[]{t1, t2, t3, t4, t5, t6, t7, t8};
        }
    }

    @Value(staticConstructor = "of")
    public static class Vertex9<T1 extends Vec<?, ?>, T2 extends Vec<?, ?>, T3 extends Vec<?, ?>, T4 extends Vec<?, ?>, T5 extends Vec<?, ?>, T6 extends Vec<?, ?>, T7 extends Vec<?, ?>, T8 extends Vec<?, ?>, T9 extends Vec<?, ?>> extends Vertex {
        @NotNull T1 t1;
        @NotNull T2 t2;
        @NotNull T3 t3;
        @NotNull T4 t4;
        @NotNull T5 t5;
        @NotNull T6 t6;
        @NotNull T7 t7;
        @NotNull T8 t8;
        @NotNull T9 t9;

        @Override
        public int size() {
            return 9;
        }

        @Override
        public Vec<?, ?>[] array() {
            return new Vec<?, ?>[]{t1, t2, t3, t4, t5, t6, t7, t8, t9};
        }
    }

    @Value(staticConstructor = "of")
    public static class Vertex10<T1 extends Vec<?, ?>, T2 extends Vec<?, ?>, T3 extends Vec<?, ?>, T4 extends Vec<?, ?>, T5 extends Vec<?, ?>, T6 extends Vec<?, ?>, T7 extends Vec<?, ?>, T8 extends Vec<?, ?>, T9 extends Vec<?, ?>, T10 extends Vec<?, ?>> extends Vertex {
        @NotNull T1 t1;
        @NotNull T2 t2;
        @NotNull T3 t3;
        @NotNull T4 t4;
        @NotNull T5 t5;
        @NotNull T6 t6;
        @NotNull T7 t7;
        @NotNull T8 t8;
        @NotNull T9 t9;
        @NotNull T10 t10;

        @Override
        public int size() {
            return 10;
        }

        @Override
        public Vec<?, ?>[] array() {
            return new Vec<?, ?>[]{t1, t2, t3, t4, t5, t6, t7, t8, t9, t10};
        }
    }

    @Value(staticConstructor = "of")
    public static class Vertex11<T1 extends Vec<?, ?>, T2 extends Vec<?, ?>, T3 extends Vec<?, ?>, T4 extends Vec<?, ?>, T5 extends Vec<?, ?>, T6 extends Vec<?, ?>, T7 extends Vec<?, ?>, T8 extends Vec<?, ?>, T9 extends Vec<?, ?>, T10 extends Vec<?, ?>, T11 extends Vec<?, ?>> extends Vertex {
        @NotNull T1 t1;
        @NotNull T2 t2;
        @NotNull T3 t3;
        @NotNull T4 t4;
        @NotNull T5 t5;
        @NotNull T6 t6;
        @NotNull T7 t7;
        @NotNull T8 t8;
        @NotNull T9 t9;
        @NotNull T10 t10;
        @NotNull T11 t11;

        @Override
        public int size() {
            return 11;
        }

        @Override
        public Vec<?, ?>[] array() {
            return new Vec<?, ?>[]{t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11};
        }
    }

    @Value(staticConstructor = "of")
    public static class Vertex12<T1 extends Vec<?, ?>, T2 extends Vec<?, ?>, T3 extends Vec<?, ?>, T4 extends Vec<?, ?>, T5 extends Vec<?, ?>, T6 extends Vec<?, ?>, T7 extends Vec<?, ?>, T8 extends Vec<?, ?>, T9 extends Vec<?, ?>, T10 extends Vec<?, ?>, T11 extends Vec<?, ?>, T12 extends Vec<?, ?>> extends Vertex {
        @NotNull T1 t1;
        @NotNull T2 t2;
        @NotNull T3 t3;
        @NotNull T4 t4;
        @NotNull T5 t5;
        @NotNull T6 t6;
        @NotNull T7 t7;
        @NotNull T8 t8;
        @NotNull T9 t9;
        @NotNull T10 t10;
        @NotNull T11 t11;
        @NotNull T12 t12;

        @Override
        public int size() {
            return 12;
        }

        @Override
        public Vec<?, ?>[] array() {
            return new Vec<?, ?>[]{t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12};
        }
    }

    @Value(staticConstructor = "of")
    public static class Vertex13<T1 extends Vec<?, ?>, T2 extends Vec<?, ?>, T3 extends Vec<?, ?>, T4 extends Vec<?, ?>, T5 extends Vec<?, ?>, T6 extends Vec<?, ?>, T7 extends Vec<?, ?>, T8 extends Vec<?, ?>, T9 extends Vec<?, ?>, T10 extends Vec<?, ?>, T11 extends Vec<?, ?>, T12 extends Vec<?, ?>, T13 extends Vec<?, ?>> extends Vertex {
        @NotNull T1 t1;
        @NotNull T2 t2;
        @NotNull T3 t3;
        @NotNull T4 t4;
        @NotNull T5 t5;
        @NotNull T6 t6;
        @NotNull T7 t7;
        @NotNull T8 t8;
        @NotNull T9 t9;
        @NotNull T10 t10;
        @NotNull T11 t11;
        @NotNull T12 t12;
        @NotNull T13 t13;

        @Override
        public int size() {
            return 13;
        }

        @Override
        public Vec<?, ?>[] array() {
            return new Vec<?, ?>[]{t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13};
        }
    }

    @Value(staticConstructor = "of")
    public static class Vertex14<T1 extends Vec<?, ?>, T2 extends Vec<?, ?>, T3 extends Vec<?, ?>, T4 extends Vec<?, ?>, T5 extends Vec<?, ?>, T6 extends Vec<?, ?>, T7 extends Vec<?, ?>, T8 extends Vec<?, ?>, T9 extends Vec<?, ?>, T10 extends Vec<?, ?>, T11 extends Vec<?, ?>, T12 extends Vec<?, ?>, T13 extends Vec<?, ?>, T14 extends Vec<?, ?>> extends Vertex {
        @NotNull T1 t1;
        @NotNull T2 t2;
        @NotNull T3 t3;
        @NotNull T4 t4;
        @NotNull T5 t5;
        @NotNull T6 t6;
        @NotNull T7 t7;
        @NotNull T8 t8;
        @NotNull T9 t9;
        @NotNull T10 t10;
        @NotNull T11 t11;
        @NotNull T12 t12;
        @NotNull T13 t13;
        @NotNull T14 t14;

        @Override
        public int size() {
            return 14;
        }

        @Override
        public Vec<?, ?>[] array() {
            return new Vec<?, ?>[]{t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14};
        }
    }

    @Value(staticConstructor = "of")
    public static class Vertex15<T1 extends Vec<?, ?>, T2 extends Vec<?, ?>, T3 extends Vec<?, ?>, T4 extends Vec<?, ?>, T5 extends Vec<?, ?>, T6 extends Vec<?, ?>, T7 extends Vec<?, ?>, T8 extends Vec<?, ?>, T9 extends Vec<?, ?>, T10 extends Vec<?, ?>, T11 extends Vec<?, ?>, T12 extends Vec<?, ?>, T13 extends Vec<?, ?>, T14 extends Vec<?, ?>, T15 extends Vec<?, ?>> extends Vertex {
        @NotNull T1 t1;
        @NotNull T2 t2;
        @NotNull T3 t3;
        @NotNull T4 t4;
        @NotNull T5 t5;
        @NotNull T6 t6;
        @NotNull T7 t7;
        @NotNull T8 t8;
        @NotNull T9 t9;
        @NotNull T10 t10;
        @NotNull T11 t11;
        @NotNull T12 t12;
        @NotNull T13 t13;
        @NotNull T14 t14;
        @NotNull T15 t15;

        @Override
        public int size() {
            return 15;
        }

        @Override
        public Vec<?, ?>[] array() {
            return new Vec<?, ?>[]{t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15};
        }
    }

    @Value(staticConstructor = "of")
    public static class Vertex16<T1 extends Vec<?, ?>, T2 extends Vec<?, ?>, T3 extends Vec<?, ?>, T4 extends Vec<?, ?>, T5 extends Vec<?, ?>, T6 extends Vec<?, ?>, T7 extends Vec<?, ?>, T8 extends Vec<?, ?>, T9 extends Vec<?, ?>, T10 extends Vec<?, ?>, T11 extends Vec<?, ?>, T12 extends Vec<?, ?>, T13 extends Vec<?, ?>, T14 extends Vec<?, ?>, T15 extends Vec<?, ?>, T16 extends Vec<?, ?>> extends Vertex {
        @NotNull T1 t1;
        @NotNull T2 t2;
        @NotNull T3 t3;
        @NotNull T4 t4;
        @NotNull T5 t5;
        @NotNull T6 t6;
        @NotNull T7 t7;
        @NotNull T8 t8;
        @NotNull T9 t9;
        @NotNull T10 t10;
        @NotNull T11 t11;
        @NotNull T12 t12;
        @NotNull T13 t13;
        @NotNull T14 t14;
        @NotNull T15 t15;
        @NotNull T16 t16;

        @Override
        public int size() {
            return 16;
        }

        @Override
        public Vec<?, ?>[] array() {
            return new Vec<?, ?>[]{t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15, t16};
        }
    }

    @Value(staticConstructor = "of")
    public static class Vertex17<T1 extends Vec<?, ?>, T2 extends Vec<?, ?>, T3 extends Vec<?, ?>, T4 extends Vec<?, ?>, T5 extends Vec<?, ?>, T6 extends Vec<?, ?>, T7 extends Vec<?, ?>, T8 extends Vec<?, ?>, T9 extends Vec<?, ?>, T10 extends Vec<?, ?>, T11 extends Vec<?, ?>, T12 extends Vec<?, ?>, T13 extends Vec<?, ?>, T14 extends Vec<?, ?>, T15 extends Vec<?, ?>, T16 extends Vec<?, ?>, T17 extends Vec<?, ?>> extends Vertex {
        @NotNull T1 t1;
        @NotNull T2 t2;
        @NotNull T3 t3;
        @NotNull T4 t4;
        @NotNull T5 t5;
        @NotNull T6 t6;
        @NotNull T7 t7;
        @NotNull T8 t8;
        @NotNull T9 t9;
        @NotNull T10 t10;
        @NotNull T11 t11;
        @NotNull T12 t12;
        @NotNull T13 t13;
        @NotNull T14 t14;
        @NotNull T15 t15;
        @NotNull T16 t16;
        @NotNull T17 t17;

        @Override
        public int size() {
            return 17;
        }

        @Override
        public Vec<?, ?>[] array() {
            return new Vec<?, ?>[]{t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15, t16, t17};
        }
    }

    @Value(staticConstructor = "of")
    public static class Vertex18<T1 extends Vec<?, ?>, T2 extends Vec<?, ?>, T3 extends Vec<?, ?>, T4 extends Vec<?, ?>, T5 extends Vec<?, ?>, T6 extends Vec<?, ?>, T7 extends Vec<?, ?>, T8 extends Vec<?, ?>, T9 extends Vec<?, ?>, T10 extends Vec<?, ?>, T11 extends Vec<?, ?>, T12 extends Vec<?, ?>, T13 extends Vec<?, ?>, T14 extends Vec<?, ?>, T15 extends Vec<?, ?>, T16 extends Vec<?, ?>, T17 extends Vec<?, ?>, T18 extends Vec<?, ?>> extends Vertex {
        @NotNull T1 t1;
        @NotNull T2 t2;
        @NotNull T3 t3;
        @NotNull T4 t4;
        @NotNull T5 t5;
        @NotNull T6 t6;
        @NotNull T7 t7;
        @NotNull T8 t8;
        @NotNull T9 t9;
        @NotNull T10 t10;
        @NotNull T11 t11;
        @NotNull T12 t12;
        @NotNull T13 t13;
        @NotNull T14 t14;
        @NotNull T15 t15;
        @NotNull T16 t16;
        @NotNull T17 t17;
        @NotNull T18 t18;

        @Override
        public int size() {
            return 18;
        }

        @Override
        public Vec<?, ?>[] array() {
            return new Vec<?, ?>[]{t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15, t16, t17, t18};
        }
    }

}
