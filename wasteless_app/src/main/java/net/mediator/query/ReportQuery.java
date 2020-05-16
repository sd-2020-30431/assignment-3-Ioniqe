package net.mediator.query;

import net.factory.ReportType;
import net.mediator.interfaces.Request;

public class ReportQuery implements Request {
    private String reportType;
    private String username;

    public ReportQuery(String reportType, String username) {
        this.reportType = reportType;
        this.username = username;
    }

    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
