package net.mediator.handler;

import net.mediator.interfaces.Handler;
import net.mediator.query.ReportQuery;
import net.mediator.response.FindReportQueryResponse;
import net.service.query.ReportServiceQuery;
import org.springframework.stereotype.Component;

@Component
public class ReadReportQueryHandler implements Handler<ReportQuery, FindReportQueryResponse> {
    private final ReportServiceQuery reportServiceQuery;

    public ReadReportQueryHandler(ReportServiceQuery reportServiceQuery) {
        this.reportServiceQuery = reportServiceQuery;
    }

    @Override
    public FindReportQueryResponse handle(ReportQuery request) {
        return new FindReportQueryResponse(reportServiceQuery.getReport(request.getReportType(), request.getUsername()));
    }
}
