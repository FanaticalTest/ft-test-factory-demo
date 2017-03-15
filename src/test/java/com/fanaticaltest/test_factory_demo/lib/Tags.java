package com.fanaticaltest.test_factory_demo.lib;

import java.util.Collection;

public final class Tags {

  public static String getId(Collection<String> tags) {
    return getSpecificTag(tags, "@id=");
  }

  public static String getFeature(Collection<String> tags) {
    return getSpecificTag(tags, "@feature=");
  }

  public static String getProject(Collection<String> tags) { return getSpecificTag(tags, "@project="); }

  public static String getProjectId(Collection<String> tags) { return getSpecificTag(tags, "@projectid="); }

  public static String getTestSuite(Collection<String> tags) { return getSpecificTag(tags, "@testsuite="); }

  public static String getSpecificTag(Collection<String> tags, String tagPrefix) {
    for (String tag : tags) {
      if (tag.toLowerCase().startsWith(tagPrefix)) {
        return tag.substring(tagPrefix.length(), tag.length());
      }
    }
    return "Unknown or tag not well formatted";
  }

}
