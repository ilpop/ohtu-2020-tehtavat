/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.verkkokauppa;


import org.junit.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import org.junit.Test;


/**
 *
 * @author iilkka
 */
public class VerkkokauppaTest {
    
    Pankki pankki = mock(Pankki.class);
    Viitegeneraattori viite = mock(Viitegeneraattori.class);
    Varasto varasto = mock(Varasto.class);
    Kauppa k = new Kauppa(varasto, pankki, viite);
    
    @Test
    public void ostoksenPaatyttyaPankinMetodiaTilisiirtoKutsutaan() {
    
    when(viite.uusi()).thenReturn(42);
    when(varasto.saldo(1)).thenReturn(10);
    when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
    
    k.aloitaAsiointi();
    k.lisaaKoriin(1);
    k.tilimaksu("pekka", "12345");
    
    verify(pankki).tilisiirto(anyString(), anyInt(), anyString(), anyString(), anyInt());
    }
    
    @Test
    public void ostoksetTehdaanJaTilisiirrossaOnOikeatArvot() {
  
    when(viite.uusi()).thenReturn(52);
    when(varasto.saldo(1)).thenReturn(10);
    when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
    
    k.aloitaAsiointi();
    k.lisaaKoriin(1);
    k.tilimaksu("pekka", "12345");
    
    verify(pankki).tilisiirto("pekka", 52, "12345", "33333-44455", 5);
        //pankki.tilisiirto(nimi, 0, tililta, tilille, 0)
    }
    
    @Test
    public void ostoksetJoissaUseampiTuoteJaTilisiirrossaOikeatArvot() {
            
    when(viite.uusi()).thenReturn(421);
    when(varasto.saldo(1)).thenReturn(10);
    when(varasto.saldo(2)).thenReturn(5);
    when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
    when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "leipä", 2));
    
    k.aloitaAsiointi();
    k.lisaaKoriin(1);
    k.lisaaKoriin(2);
    k.tilimaksu("pekka", "12345");
    
    verify(pankki).tilisiirto("pekka", 421, "12345", "33333-44455", 7);
    }
    
    @Test
    public void ostoksetUseampiSamaTuoteJaTilisiirrossaOikeatArvot() {
            
    when(viite.uusi()).thenReturn(421421421);
    when(varasto.saldo(2)).thenReturn(5);
    when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "leipä", 2));
    
    k.aloitaAsiointi();
    k.lisaaKoriin(2);
    k.lisaaKoriin(2);
    k.tilimaksu("pekka", "12345");
    
    verify(pankki).tilisiirto("pekka", 421421421, "12345", "33333-44455", 4);
    
    }
    
    @Test
    public void ostoksetJossaUseampiTuoteMuttaToinenTuoteLoppuJaTilisiirrossaOikeatArvot () {
        
    when(viite.uusi()).thenReturn(9999);
    when(varasto.saldo(2)).thenReturn(5);
    when(varasto.saldo(3)).thenReturn(0);
    when(varasto.haeTuote(3)).thenReturn(new Tuote(3, "käsidesi", 99));
    when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "leipä", 2));
    
    k.aloitaAsiointi();
    k.lisaaKoriin(2);
    k.lisaaKoriin(3);
    k.tilimaksu("pekka", "12345");
    
    verify(pankki).tilisiirto("pekka", 9999, "12345", "33333-44455", 2);
    
    }
    
    @Test
    public void aloitaAsiointiAloittaaUudenAsioinnin() {

        when(viite.uusi()).
                thenReturn(1).
                thenReturn(2).
                thenReturn(3);
    
    when(varasto.saldo(2)).thenReturn(5);
    when(varasto.saldo(3)).thenReturn(10);
    when(varasto.haeTuote(3)).thenReturn(new Tuote(3, "käsidesi", 99));
    when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "leipä", 2));

      

        k.aloitaAsiointi();
        k.lisaaKoriin(3);
        k.tilimaksu("asta", "1111");

        // varmistetaan, että nyt käytössä ensimmäisenä pyydetty viite
    
    verify(pankki).tilisiirto("asta", 1, "1111", "33333-44455", 99);
        
        k.aloitaAsiointi();
        k.lisaaKoriin(2);
        k.tilimaksu("asta", "1222");

        // ... toisena pyydetty viite
  verify(pankki).tilisiirto("asta", 2, "1222", "33333-44455", 2);  
        
        k.aloitaAsiointi();
        k.lisaaKoriin(2);
         k.lisaaKoriin(2);
         k.poistaKorista(2);
        k.tilimaksu("asta", "1333");

        // ... ja kolmantena pyydetty viite        
  verify(pankki).tilisiirto("asta", 3, "1333", "33333-44455", 4);  
      
    }
               
}
