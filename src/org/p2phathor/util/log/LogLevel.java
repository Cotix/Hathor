package org.p2phathor.util.log;

/**
 * The loglevels used by the Log class.
 */
public enum LogLevel {
    VERBOSE(0), //Meant to be used as much as possible. Use this when you print debug information.
    INFO(1), //Use this when you want to report some interesting info, for example things like a new incomming connection
    WARNING(2),  //Use this if something went wrong, but it is probably not going to break a whole lot
    ODD(3),  //For unexpected warnings.
    ERROR(4),  //Something went wrong that can and/or will break stuff!
    HIGHEST(99);

    LogLevel(int v) {

    }

}
