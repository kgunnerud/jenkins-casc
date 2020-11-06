package ci

import java.util.stream.Collectors

static void main(String[] args) {
    //def changed = findChangedPaths("apps/derp/dorp\nlibs/d1/src\n.dockerFile\napps/derp/dorp\n");
    //println changed.stream().map(e -> e.toString()).collect(Collectors.joining("\n"))
}

@NonCPS
static List<ChangedPath> findChangedPaths(String gitChanges) {
    def uniques = new HashSet();
    return (gitChanges.split("\n") as List)
            .collect {toChangedPath(it) }
            .findAll {it != null }
            .findAll {uniques.add(it.name) }
}

private static ChangedPath toChangedPath(String input) {
    def matcher = input =~ "^(apps|libs)/(?<name>.*?)/.*"
    if (matcher.matches()) {
        if (input.startsWith("apps")) {
            def name = matcher.group("name");
            return new ChangedPath(type: Type.APP, path: "apps/" + name, name: name);
        } else if (input.startsWith("libs")) {
            def name = matcher.group("name");
            return new ChangedPath(type: Type.LIB, path: "libs/" + name, name: name);
        }
    }
    return null;
}

class ChangedPath {
    String name;
    Type type;
    String path;

    @NonCPS
    String toString() {
        return "ChangedPath: [name: $name, type: $type, path: $path]"
    }
}

enum Type {
    APP,
    LIB
}

return this;
