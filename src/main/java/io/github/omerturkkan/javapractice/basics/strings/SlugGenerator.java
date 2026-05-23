package io.github.omerturkkan.javapractice.basics.strings;

public class SlugGenerator {

    public static void main(String[] args) {
        String title = "  Hello World! Java & Programming 101  ";

        String slug = title
                .trim()
                .toLowerCase()
                .replaceAll("[^a-z0-9 ]", "")
                .replaceAll(" +", " ")
                .replace(" ", "-");

        System.out.println("Original : \"" + title + "\"");
        System.out.println("Slug     : \"" + slug + "\"");
        System.out.println("Length   : " + slug.length());
        System.out.println("Starts with 'hello' : " + slug.startsWith("hello"));
        System.out.println("Ends with '101'     : " + slug.endsWith("101"));
    }
}