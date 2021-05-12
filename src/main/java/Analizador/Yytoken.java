/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 package Analizador;

/**
 *
 * @author Cristian
 */
public class Yytoken {

    public String m_text;
    public String m_patron;
    public String m_token;

    public Yytoken(String m_text, String m_patron, String m_token) {
        this.m_text = m_text;
        this.m_patron = m_patron;
        this.m_token = m_token;
    }

   

    public String getM_text() {
        return m_text;
    }

    public void setM_text(String m_text) {
        this.m_text = m_text;
    }

    public String getM_patron() {
        return m_patron;
    }

    public void setM_patron(String m_patron) {
        this.m_patron = m_patron;
    }

    public String getM_token() {
        return m_token;
    }

    public void setM_token(String m_token) {
        this.m_token = m_token;
    }

    


//    public String toStringC() {
//        return "Token: #" + m_index + ",\tlexema:\t'" + m_text  + "'\tpatron:\t'" + m_patron + "' \tvalor:\t'" + m_descripcion;
//    }
//
//    public String toStringError() {
//        return m_descripcion + " el simbolo '" + m_text + "' no es válido\tlinea:" + m_line + "\tColumna:" + m_charBegin;
//    }

    @Override
    public String toString() {
        return m_text ;
    }
}
