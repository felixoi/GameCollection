package net.felixoi.gamecollection;

public final class Permissions {

    public static String INFO = buildPermission("info");

    public static class Arena {
        public static String ADD = buildPermission("arena.add");
        public static String REMOVE = buildPermission("arena.remove");
        public static String LIST = buildPermission("arena.list");
        public static String RENAME = buildPermission("arena.rename");
        public static String JOIN = buildPermission("arena.join");
    }

    public static class Sign {
        public static String INFO = buildPermission("sign.info");
        public static String ADD = buildPermission("sign.add");
        public static String REMOVE = buildPermission("sign.remove");
    }

    private static String PREFIX = "gamecollection";
    private static String buildPermission(String permission) {
        return PREFIX + "." + permission;
    }

}
