package pl.com.bottega.cms.model;

import java.time.LocalTime;

public interface ShowExporter {

    void addId(Long id);

    void addTime(LocalTime time);
}
