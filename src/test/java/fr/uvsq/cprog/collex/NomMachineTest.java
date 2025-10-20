package fr.uvsq.cprog.collex;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

/**
* Tests unitaires de la classe {@link NomMachine}.
*/
public class NomMachineTest {

  /**
  * Teste la création valide d'un nom de machine.
  */
  @Test
  public void testCreationValide() {
    NomMachine nm = new NomMachine("www", "uvsq.fr");
    assertEquals("www.uvsq.fr", nm.toString());
  }

  /**
  * Teste la création avec un nom null (devrait lever une exception).
  */
  @Test(expected = IllegalArgumentException.class)
  public void testCreationInvalide_Null() {
    new NomMachine(null, "uvsq.fr");
  }

  /**
   * Teste la création avec un nom vide (devrait lever une exception).
   */
  @Test(expected = IllegalArgumentException.class)
  public void testCreationInvalide_Vide() {
    new NomMachine("", "uvsq.fr");
  }

  /**
   * Teste les méthodes equals et hashCode.
   */
  @Test
  public void testEqualsEtHashCode() {
    NomMachine nm1 = new NomMachine("www", "uvsq.fr");
    NomMachine nm2 = new NomMachine("www", "uvsq.fr");
    NomMachine nm3 = new NomMachine("poste", "uvsq.fr");

    assertEquals(nm1, nm2);
    assertNotEquals(nm1, nm3);
    assertEquals(nm1.hashCode(), nm2.hashCode());
    assertNotEquals(nm1.hashCode(), nm3.hashCode());
  }
}