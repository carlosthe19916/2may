package may.config.idm;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

public class CurrencyRepresentation {

    @NotNull
    @Size(min = 3, max = 3)
    public String codigo;
    public BigDecimal tipoCambio;

    public CurrencyRepresentation() {
    }

    public CurrencyRepresentation(String codigo) {
        this.codigo = codigo;
    }

    public CurrencyRepresentation(String codigo, BigDecimal tipoCambio) {
        this.codigo = codigo;
        this.tipoCambio = tipoCambio;
    }
}
