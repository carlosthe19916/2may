package may.config.idm;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class InvoiceRepresentation {

    @NotNull
    public String tipoComprobante;

    public Date fechaEmision;
    public Date fechaVencimiento;

    @Valid
    @NotNull
    public CurrencyRepresentation moneda;

}
