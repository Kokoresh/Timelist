package io.github.kokoresh.timelist;

import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public final class Parser {
    // Smart method (can find current week in file)
    private static void findWeek(final XmlPullParser xmlParser) throws XmlPullParserException, IOException {
        final boolean lower = (Calendar.getInstance().get(Calendar.WEEK_OF_YEAR) % 2 == 0);
        final boolean upper = !lower;
        while (xmlParser.getEventType() != XmlPullParser.END_DOCUMENT) {
            if (xmlParser.getEventType() == XmlPullParser.START_TAG
                    && xmlParser.getName().equalsIgnoreCase("week")) {
                final String position =
                        xmlParser.getAttributeValue(null, "position");
                if ((lower && position.equalsIgnoreCase("lower"))
                        || (upper && position.equalsIgnoreCase("upper"))) {
                    break;
                }
            }
            xmlParser.next();
        }
    }

    private static Lesson parseLesson(int n, final XmlPullParser xmlParser) {
        return LessonFabric.create(
                n,
                xmlParser.getAttributeValue(null, "hall"),
                xmlParser.getAttributeValue(null, "name"),
                xmlParser.getAttributeValue(null, "lecturer"));
    }

    private static Day parseDay(final XmlPullParser xmlParser) throws XmlPullParserException, IOException {
        final List<Lesson> list = new ArrayList<>();
        final String dayName = xmlParser.getAttributeValue(null, "name");
        int n = -1;
        while (xmlParser.getEventType() != XmlPullParser.END_DOCUMENT) {
            final String tag = xmlParser.getName();
            if (xmlParser.getEventType() == XmlPullParser.END_TAG
                    && tag.equalsIgnoreCase("day")) {
                break;
            }
            if (xmlParser.getEventType() == XmlPullParser.START_TAG
                    && tag.equalsIgnoreCase("lesson")) {
                list.add(parseLesson(++n, xmlParser));
            }
            xmlParser.next();
        }
        return new Day(dayName, list);
    }

    public static List<Day> parseDaysFromXml(final XmlPullParser xmlParser) {
        final List<Day> res = new ArrayList<>();
        try {
            findWeek(xmlParser);
            while (xmlParser.getEventType() != XmlPullParser.END_DOCUMENT) {
                final String tag = xmlParser.getName();
                if (xmlParser.getEventType() == XmlPullParser.END_TAG
                        && tag.equalsIgnoreCase("week")) {
                    return res;
                }
                if (xmlParser.getEventType() == XmlPullParser.START_TAG
                        && tag.equalsIgnoreCase("Day")) {
                    res.add(parseDay(xmlParser));
                }
                xmlParser.next();
            }
        } catch (XmlPullParserException e) {
            Log.e("WeekParser", e.getLocalizedMessage());
        } catch (IOException e) {
            Log.e("WeekParser", e.getLocalizedMessage());
        }
        return res;
    }

    public static Day getCurrentDay(final XmlPullParser xmlParser) {
        return parseDaysFromXml(xmlParser).get(Calendar.getInstance().get(Calendar.DAY_OF_WEEK) - 2);
    }
}
