package io.github.kokoresh.timelist;

import android.support.annotation.NonNull;
import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// Класс связывает имя группы для представления его в списке и соответсвующий ресурс
public class GroupView {
    interface Resource {
        XmlPullParser GetXmlParser();
    }

    private final String name;
    private final Resource resource;

    public String getName() {
        return name;
    }

    public List<Day> getDays() {
        return Parser.parseDaysFromXml(resource.GetXmlParser());
    }

    public GroupView(final String name, final Resource res) {
        this.name = name;
        resource = res;
    }
}
