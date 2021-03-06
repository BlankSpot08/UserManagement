package com.example.inklow.dao;

import com.example.inklow.entities.ReportInquiryDetails;
import com.example.inklow.entities.ReportInquiryReply;

import java.util.List;

public interface ReportInquiryReplyDao {
    List<ReportInquiryReply> getListOfReportInquiryReply();

    ReportInquiryReply getReportInquiryDetailsReplyById(ReportInquiryDetails reportInquiryDetails);

    ReportInquiryReply addReportInquiryDetailsReply(ReportInquiryReply replyInquiryReply);

    ReportInquiryReply deleteReportInquiryDetailsReply(ReportInquiryReply reportInquiryReply);
}
