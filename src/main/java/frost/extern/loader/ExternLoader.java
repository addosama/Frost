package frost.extern.loader;

public @interface ExternLoader {
    enum Type{
        Module,

    }
    Type type();
}
