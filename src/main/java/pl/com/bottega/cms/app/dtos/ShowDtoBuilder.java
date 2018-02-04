package pl.com.bottega.cms.app.dtos;

import pl.com.bottega.cms.model.ShowExporter;

import java.time.LocalTime;

public class ShowDtoBuilder implements ShowExporter {

    private ShowDto showDto = new ShowDto();

    public ShowDto build() {
        ShowDto showDtoResult = showDto;
        showDto = new ShowDto();
        return showDtoResult;
    }

    @Override
    public void addId(Long id) {
        showDto.setId(id);
    }

    @Override
    public void addTime(LocalTime time) {
        showDto.setTime(time);
    }

}
