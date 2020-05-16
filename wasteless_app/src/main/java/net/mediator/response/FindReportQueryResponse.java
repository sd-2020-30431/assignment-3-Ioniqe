package net.mediator.response;

import net.mediator.interfaces.Response;
import net.model.CreatedReport;
import net.model.dto.ReportDTO;

public class FindReportQueryResponse implements Response {
    private CreatedReport reportDTO;

    public FindReportQueryResponse(CreatedReport reportDTO) {
        this.reportDTO = reportDTO;
    }

    public CreatedReport getReportDTO() {
        return reportDTO;
    }

    public void setReportDTO(CreatedReport reportDTO) {
        this.reportDTO = reportDTO;
    }
}
