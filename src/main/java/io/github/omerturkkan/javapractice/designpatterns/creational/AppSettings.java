package io.github.omerturkkan.javapractice.designpatterns.creational;

public class AppSettings {
    // The one and only instance, created when the class is loaded
    private static final AppSettings INSTANCE = new AppSettings();

    private String theme = "light";
    private String language = "en";

    // Private constructor: 'new AppSettings()' is not allowed outside this class
    private AppSettings() {
    }

    public static AppSettings getInstance() {
        return INSTANCE;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public static void main(String[] args) {
        AppSettings first = AppSettings.getInstance();
        AppSettings second = AppSettings.getInstance();

        System.out.println("Same instance : " + (first == second));

        // A change made through one reference is visible through the other
        first.setTheme("dark");
        second.setLanguage("tr");

        System.out.println("Theme         : " + second.getTheme());
        System.out.println("Language      : " + first.getLanguage());
    }
}
