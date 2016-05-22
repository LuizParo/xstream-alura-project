package br.com.caelum.xstream;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

import com.thoughtworks.xstream.converters.ConversionException;
import com.thoughtworks.xstream.converters.SingleValueConverter;

public class PrecoSimplesConverter implements SingleValueConverter {

    @SuppressWarnings("unchecked")
    @Override
    public boolean canConvert(@SuppressWarnings("rawtypes") Class type) {
        return type.isAssignableFrom(Double.class);
    }

    @Override
    public String toString(Object value) {
        Double valor = (Double) value;
        Locale brasil = new Locale("pt","br");
        NumberFormat formatador = NumberFormat.getCurrencyInstance(brasil);
        String valorEmReais = formatador.format(valor);
        return valorEmReais;
    }

    @Override
    public Object fromString(String valor) {
        Locale brasil = new Locale("pt","br");
        NumberFormat formatador = NumberFormat.getCurrencyInstance(brasil);
        try {
            return formatador.parse(valor);
        } catch (ParseException e) {
            throw new ConversionException(e);
        }
    }
}