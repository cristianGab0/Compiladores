/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lexico;

/**
 *
 * @author Cristian
 */
public class Yytoken {

    public int m_index;
    public String m_descripcion;
    public String m_text;
    public String m_patron;
    public int m_line;
    public int m_charBegin;
    public int m_charEnd;

    Yytoken(int index, String descripcion, String text, String patron, int line, int charBegin, int charEnd) {
        m_index = index;
        m_descripcion = new String(descripcion);
        m_text = new String(text);
        m_patron = new String(patron);
        m_line = line;
        m_charBegin = charBegin;
        m_charEnd = charEnd;
    }

    @Override
    public String toString() {
        return "Yytoken{" + "m_descripcion=" + m_descripcion + ", m_text=" + m_text + '}';
    }

    public String toStringC() {
        return "Token: #" + m_index + ",\tlexema:\t'" + m_text  + "'\tpatron:\t'" + m_patron + "' \tvalor:\t'" + m_descripcion;
    }

    public String toStringError() {
        return m_descripcion + " el simbolo '" + m_text + "' no es válido\tlinea:" + m_line + "\tColumna:" + m_charBegin;
    }
}
