package org.riskengine.core.util;

import org.riskengine.core.model.SimpleRule;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.stream.Stream;

public class RuleReader {

    private static final String FOLDER = "cfg";
    private static final String FILE_SUFFIX = "rule_";

    public static Map<Long, SimpleRule> readRule() throws IOException {

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        URL url = classLoader.getResource(FOLDER);

        if (url == null) {
            throw new RuntimeException("cfg directory not found");
        }

        List<String> ruleString;
        if (url.getProtocol().equals("file")) {
            ruleString = getRulesStringFromFileSystem(url);
        } else if (url.getProtocol().equals("jar")) {
            ruleString = getRulesStringFromJar(url);
        } else {
            throw new UnsupportedOperationException("file system not supported");
        }


        ObjectMapper mapper = new ObjectMapper();

        Map<Long, SimpleRule> ruleMap = new HashMap<>();

        for (var rule : ruleString) {
            SimpleRule parsdSimpleRule = mapper.readValue(rule, SimpleRule.class);
            ruleMap.put(parsdSimpleRule.getId(), parsdSimpleRule);
        }

        return ruleMap;
    }

    private static List<String> getRulesStringFromFileSystem(URL url) {

        try (Stream<Path> paths = Files.walk(Paths.get(url.toURI()), 1)) {
            return paths
                    .filter(Files::isRegularFile)
                    .filter((p) -> p.getFileName().startsWith(FILE_SUFFIX))
                    .map(p -> FOLDER + "/" + p.getFileName())
                    .toList();
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    private static List<String> getRulesStringFromJar(URL url) throws IOException {

        String jarPath = url.getPath().substring(5, url.getPath().indexOf("!"));
        List<String> result = new ArrayList<>();

        try (JarFile jar = new JarFile(URLDecoder.decode(jarPath, StandardCharsets.UTF_8))) {
            Enumeration<JarEntry> entries = jar.entries();
            while (entries.hasMoreElements()) {
                JarEntry entry = entries.nextElement();
                if (entry.getName().startsWith(FOLDER + "/")
                        && !entry.isDirectory()) {
                    result.add(entry.getName());
                }
            }
        }
        return result;


    }


}
