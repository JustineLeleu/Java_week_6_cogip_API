package week6.java.cogip.entities;

public class Invoice {
    private short id;
    private String timestamp;
    private short invoiceCompanyId;
    private short invoiceContactId;

    public Invoice(short id, String timestamp, short invoiceCompanyId, short invoiceContactId) {
        this.id = id;
        this.timestamp = timestamp;
        this.invoiceCompanyId = invoiceCompanyId;
        this.invoiceContactId = invoiceContactId;
    }

    public short getId() {
        return id;
    }

    public void setId(short id) {
        this.id = id;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public short getInvoiceCompanyId() {
        return invoiceCompanyId;
    }

    public void setInvoiceCompanyId(short invoiceCompanyId) {
        this.invoiceCompanyId = invoiceCompanyId;
    }

    public short getInvoiceContactId() {
        return invoiceContactId;
    }

    public void setInvoiceContactId(short invoiceContactId) {
        this.invoiceContactId = invoiceContactId;
    }
}
