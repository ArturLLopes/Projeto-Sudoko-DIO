package ui.custom.input;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import java.util.List;

import static java.util.Objects.isNull;

public class NumberTextLimit extends PlainDocument {

    private final List<String> NUMBERS = List.of("1", "2", "3", "4", "5", "6", "7", "8", "9");
    //controlar o que pode ser digitado em campos de texto
    @Override
    public void insertString(final int offs, final String str, final AttributeSet a) throws BadLocationException {
        // método é chamado toda vez que o usuário tenta digitar algo no campo de texto
        if (isNull(str) || (!NUMBERS.contains(str))) return;

        if (getLength() + str.length() <= 1){
            //Garante que apenas um único caractere seja inserido no campo
            super.insertString(offs, str, a);
        }
    }
}