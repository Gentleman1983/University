/**
 * Allgemeines Programmierpraktikum Sommersemester 2008
 * Georg-August-Universität Göttingen (Dr. H. Brosenne)
 * Projektarbeit zum Thema DamePP
 * 
 * Gruppe: DamePP-2AD
 * Teamleitung: Christian Otto
 * Projektbetreuung: David-Alexandre Guiraud
 * 
 * Entwickler:
 * Kathrin Aßhauer, Christian Beulke, Franziska Klingner,
 * Stefanie Mühlhausen, Christian Otto, Martin Schewe, 
 * Sven Withus
 */

package damepp;

/**
 * Klasse des Status-Interfaces.
 * @author Stefanie M&uuml;hlhausen
 * @author Henrik Brosenne
 */

public interface Status {

    int OK         = 0; 
    int BLACK_WINS = 1;  
    int WHITE_WINS = 2; 
    int DRAW       = 3;
    int ERROR      = 99;
}
