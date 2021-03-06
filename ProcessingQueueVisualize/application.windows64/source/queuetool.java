import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.Random; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class queuetool extends PApplet {



Random r = new Random();

List bausteine = new List();
int scroll = 5;
int scrollwert = 30;
int pos = 0;
Button enqueueButton = new Button(20, 20, 120, 60, color(85, 107, 47), "enqueue(Inhalt);", color(255, 255, 255));
Button dequeueButton = new Button(140, 20, 100, 60, color(165, 42, 42), "dequeue();", color(255, 255, 255));
Button isEmpty = new Button(240, 20, 80, 30, color(72, 61, 139), "isEmpty();", color(255, 255, 255));
Button front = new Button(320, 20, 80, 30, color(47, 79, 79), "front();", color(255, 255, 255));
Button scrollLeft = new Button(240, 50, 80, 30, color(255, 20, 147), "<<<", color(255, 255, 255));
Button scrollRight = new Button(320, 50, 80, 30, color(255, 20, 147), ">>>", color(255, 255, 255));

int bgcol = color(240, 128, 128);

public void setup() {
  PFont calibri = createFont("Calibri", 32, true);
  
  frameRate(60);
  textFont(calibri);
}

public void draw() {
  background(bgcol);
  fill(255,255,255);
  textSize(30);
  text("Queue Tool 0.7 von Tim Moeller",410,20);
  textSize(20);
  text("Tool um Warteschlangen in Java zu visualisieren",410,50);
  text("1.03.2018",410,70);
  enqueueButton.drawButton();
  dequeueButton.drawButton();
  isEmpty.drawButton();
  front.drawButton();
  scrollLeft.drawButton();
  scrollRight.drawButton();
  checkMouse();
  drawInputField();
  frontShow();
  pos = 0;
  bausteine.toFirst();
  while (bausteine.hasAccess()) {
    bausteine.getContent().drawBaustein(pos);
    bausteine.getContent().texti();
    bausteine.next();
    pos++;
  }
}

public void frontShow() {
  bausteine.toFirst();
  if (bausteine.hasAccess()) {
    fill(bausteine.getContent().farbe);
    rect((bausteine.getContent().position*50)+scroll, 260, 50, 20);
    textSize(12);
    fill(255, 255, 255);
    textAlign(LEFT, TOP);
    text("First Item", (bausteine.getContent().position*50)+scroll, 260);
  }
}

public void enqueue(String txt) {
  Baustein bs = new Baustein(txt);
  bausteine.append(bs);
  clearText();
}

public void isEmpty() {
  if (!bausteine.isEmpty()) inputText = "false";
  else inputText = "true";
}

public void dequeue() {
  bausteine.toFirst();
  if (bausteine.hasAccess())bausteine.remove();
}

public void front() {
  bausteine.toFirst();
  if (bausteine.hasAccess())inputText = bausteine.getContent().Inhalt;
  else inputText = "null";
  scroll = 5;
}

public void scrollLeft() {
  scroll=scroll+scrollwert;
}

public void scrollRight() {
  scroll=scroll-scrollwert;
}
/**
 * <p>
 * Materialien zu den zentralen NRW-Abiturpruefungen im Fach Informatik ab 2018
 * </p>
 * <p>
 * Generische Klasse List<Baustein>
 * </p>
 * <p>
 * Objekt der generischen Klasse List verwalten beliebig viele linear
 * angeordnete Objekte vom Typ Baustein. Auf hoechstens ein Listenobjekt,
 * aktuellesObjekt genannt, kann jeweils zugegriffen werden.<br />
 * Wenn eine Liste leer ist, vollstaendig durchlaufen wurde oder das aktuelle
 * Objekt am Ende der Liste geloescht wurde, gibt es kein aktuelles Objekt.<br />
 * Das erste oder das letzte Objekt einer Liste koennen durch einen Auftrag zum
 * aktuellen Objekt gemacht werden. Ausserdem kann das dem aktuellen Objekt
 * folgende Listenobjekt zum neuen aktuellen Objekt werden. <br />
 * Das aktuelle Objekt kann gelesen, veraendert oder geloescht werden. Ausserdem
 * kann vor dem aktuellen Objekt ein Listenobjekt eingefuegt werden.
 * </p>
 * 
 * @author Qualitaets- und UnterstuetzungsAgentur - Landesinstitut fuer Schule
 * @version Generisch_06 2015-10-25
 */
class List {

  /* --------- Anfang der privaten inneren Klasse -------------- */

  class ListNode {

    private Baustein contentObject;
    private ListNode next;

    /**
     * Ein neues Objekt wird erschaffen. Der Verweis ist leer.
     * 
     * @param pContent das Inhaltsobjekt vom Typ Baustein
     */
    private ListNode(Baustein pContent) {
      contentObject = pContent;
      next = null;
    }

    /**
     * Der Inhalt des Knotens wird zurueckgeliefert.
     * 
     * @return das Inhaltsobjekt des Knotens
     */
    public Baustein getContentObject() {
      return contentObject;
    }

    /**
     * Der Inhalt dieses Kontens wird gesetzt.
     * 
     * @param pContent das Inhaltsobjekt vom Typ Baustein
     */
    public void setContentObject(Baustein pContent) {
      contentObject = pContent;
    }

    /**
     * Der Nachfolgeknoten wird zurueckgeliefert.
     * 
     * @return das Objekt, auf das der aktuelle Verweis zeigt
     */
    public ListNode getNextNode() {
      return this.next;
    }

    /**
     * Der Verweis wird auf das Objekt, das als Parameter uebergeben
     * wird, gesetzt.
     * 
     * @param pNext der Nachfolger des Knotens
     */
    public void setNextNode(ListNode pNext) {
      this.next = pNext;
    }
  }

  /* ----------- Ende der privaten inneren Klasse -------------- */

  // erstes Element der Liste
  ListNode first;

  // letztes Element der Liste
  ListNode last;

  // aktuelles Element der Liste
  ListNode current;

  /**
   * Eine leere Liste wird erzeugt.
   */
  public List() {
    first = null;
    last = null;
    current = null;
  }

  /**
   * Die Anfrage liefert den Wert true, wenn die Liste keine Objekte enthaelt,
   * sonst liefert sie den Wert false.
   * 
   * @return true, wenn die Liste leer ist, sonst false
   */
  public boolean isEmpty() {
    // Die Liste ist leer, wenn es kein erstes Element gibt.
    return first == null;
  }

  /**
   * Die Anfrage liefert den Wert true, wenn es ein aktuelles Objekt gibt,
   * sonst liefert sie den Wert false.
   * 
   * @return true, falls Zugriff moeglich, sonst false
   */
  public boolean hasAccess() {
    // Es gibt keinen Zugriff, wenn current auf kein Element verweist.
    return current != null;
  }

  /**
   * Falls die Liste nicht leer ist, es ein aktuelles Objekt gibt und dieses
   * nicht das letzte Objekt der Liste ist, wird das dem aktuellen Objekt in
   * der Liste folgende Objekt zum aktuellen Objekt, andernfalls gibt es nach
   * Ausfuehrung des Auftrags kein aktuelles Objekt, d.h. hasAccess() liefert
   * den Wert false.
   */
  public void next() {
    if (this.hasAccess()) {
      current = current.getNextNode();
    }
  }

  /**
   * Falls die Liste nicht leer ist, wird das erste Objekt der Liste aktuelles
   * Objekt. Ist die Liste leer, geschieht nichts.
   */
  public void toFirst() {
    if (!isEmpty()) {
      current = first;
    }
  }

  /**
   * Falls die Liste nicht leer ist, wird das letzte Objekt der Liste
   * aktuelles Objekt. Ist die Liste leer, geschieht nichts.
   */
  public void toLast() {
    if (!isEmpty()) {
      current = last;
    }
  }

  /**
   * Falls es ein aktuelles Objekt gibt (hasAccess() == true), wird das
   * aktuelle Objekt zurueckgegeben, andernfalls (hasAccess() == false) gibt
   * die Anfrage den Wert null zurueck.
   * 
   * @return das aktuelle Objekt (vom Typ Baustein) oder null, wenn es
   *         kein aktuelles Objekt gibt
   */
  public Baustein getContent() {
    if (this.hasAccess()) {
      return current.getContentObject();
    } else {
      return null;
    }
  }

  /**
   * Falls es ein aktuelles Objekt gibt (hasAccess() == true) und pContent
   * ungleich null ist, wird das aktuelle Objekt durch pContent ersetzt. Sonst
   * geschieht nichts.
   * 
   * @param pContent
   *            das zu schreibende Objekt vom Typ Baustein
   */
  public void setContent(Baustein pContent) {
    // Nichts tun, wenn es keinen Inhalt oder kein aktuelles Element gibt.
    if (pContent != null && this.hasAccess()) { 
      current.setContentObject(pContent);
    }
  }

  /**
   * Falls es ein aktuelles Objekt gibt (hasAccess() == true), wird ein neues
   * Objekt vor dem aktuellen Objekt in die Liste eingefuegt. Das aktuelle
   * Objekt bleibt unveraendert. <br />
   * Wenn die Liste leer ist, wird pContent in die Liste eingefuegt und es
   * gibt weiterhin kein aktuelles Objekt (hasAccess() == false). <br />
   * Falls es kein aktuelles Objekt gibt (hasAccess() == false) und die Liste
   * nicht leer ist oder pContent gleich null ist, geschieht nichts.
   * 
   * @param pContent
   *            das einzufuegende Objekt vom Typ Baustein
   */
  public void insert(Baustein pContent) {
    if (pContent != null) { // Nichts tun, wenn es keinen Inhalt gibt.
      if (this.hasAccess()) { // Fall: Es gibt ein aktuelles Element.

        // Neuen Knoten erstellen.
        ListNode newNode = new ListNode(pContent); 

        if (current != first) { // Fall: Nicht an erster Stelle einfuegen.
          ListNode previous = this.getPrevious(current);
          newNode.setNextNode(previous.getNextNode());
          previous.setNextNode(newNode);
        } else { // Fall: An erster Stelle einfuegen.
          newNode.setNextNode(first);
          first = newNode;
        }
      } else { //Fall: Es gibt kein aktuelles Element.

        if (this.isEmpty()) { // Fall: In leere Liste einfuegen.

          // Neuen Knoten erstellen.
          ListNode newNode = new ListNode(pContent); 

          first = newNode;
          last = newNode;
        }
      }
    }
  }

  /**
   * Falls pContent gleich null ist, geschieht nichts.<br />
   * Ansonsten wird ein neues Objekt pContent am Ende der Liste eingefuegt.
   * Das aktuelle Objekt bleibt unveraendert. <br />
   * Wenn die Liste leer ist, wird das Objekt pContent in die Liste eingefuegt
   * und es gibt weiterhin kein aktuelles Objekt (hasAccess() == false).
   * 
   * @param pContent
   *            das anzuhaengende Objekt vom Typ Baustein
   */
  public void append(Baustein pContent) {
    if (pContent != null) { // Nichts tun, wenn es keine Inhalt gibt.

      if (this.isEmpty()) { // Fall: An leere Liste anfuegen.
        this.insert(pContent);
      } else { // Fall: An nicht-leere Liste anfuegen.

        // Neuen Knoten erstellen.
        ListNode newNode = new ListNode(pContent); 

        last.setNextNode(newNode);
        last = newNode; // Letzten Knoten aktualisieren.
      }
    }
  }

  /**
   * Falls es sich bei der Liste und pList um dasselbe Objekt handelt,
   * pList null oder eine leere Liste ist, geschieht nichts.<br />
   * Ansonsten wird die Liste pList an die aktuelle Liste angehaengt.
   * Anschliessend wird pList eine leere Liste. Das aktuelle Objekt bleibt
   * unveraendert. Insbesondere bleibt hasAccess identisch.
   * 
   * @param pList
   *            die am Ende anzuhaengende Liste vom Typ List<Baustein>
   */
  public void concat(List pList) {
    if (pList != this && pList != null && !pList.isEmpty()) { // Nichts tun,
      // wenn pList und this identisch, pList leer oder nicht existent.

      if (this.isEmpty()) { // Fall: An leere Liste anfuegen.
        this.first = pList.first;
        this.last = pList.last;
      } else { // Fall: An nicht-leere Liste anfuegen.
        this.last.setNextNode(pList.first);
        this.last = pList.last;
      }

      // Liste pList loeschen.
      pList.first = null;
      pList.last = null;
      pList.current = null;
    }
  }

  /**
   * Wenn die Liste leer ist oder es kein aktuelles Objekt gibt (hasAccess()
   * == false), geschieht nichts.<br />
   * Falls es ein aktuelles Objekt gibt (hasAccess() == true), wird das
   * aktuelle Objekt geloescht und das Objekt hinter dem geloeschten Objekt
   * wird zum aktuellen Objekt. <br />
   * Wird das Objekt, das am Ende der Liste steht, geloescht, gibt es kein
   * aktuelles Objekt mehr.
   */
  public void remove() {
    // Nichts tun, wenn es kein aktuelle Element gibt oder die Liste leer ist.
    if (this.hasAccess() && !this.isEmpty()) { 

      if (current == first) {
        first = first.getNextNode();
      } else {
        ListNode previous = this.getPrevious(current);
        if (current == last) {
          last = previous;
        }
        previous.setNextNode(current.getNextNode());
      }

      ListNode temp = current.getNextNode();
      current.setContentObject(null);
      current.setNextNode(null);
      current = temp;

      //Beim loeschen des letzten Elements last auf null setzen. 
      if (this.isEmpty()) {
        last = null;
      }
    }
  }

  /**
   * Liefert den Vorgaengerknoten des Knotens pNode. Ist die Liste leer, pNode
   * == null, pNode nicht in der Liste oder pNode der erste Knoten der Liste,
   * wird null zurueckgegeben.
   *
   * @param pNode
   *         der Knoten, dessen Vorgaenger zurueckgegeben werden soll
   * @return der Vorgaenger des Knotens pNode oder null, falls die Liste leer ist,
   *         pNode == null ist, pNode nicht in der Liste ist oder pNode der erste Knoten
   *         der Liste ist
   */
  private ListNode getPrevious(ListNode pNode) {
    if (pNode != null && pNode != first && !this.isEmpty()) {
      ListNode temp = first;
      while (temp != null && temp.getNextNode() != pNode) {
        temp = temp.getNextNode();
      }
      return temp;
    } else {
      return null;
    }
  }
}
class Baustein {
  int position = 0;
  String Inhalt = "";
  boolean mausDrauf = false;
  int farbe = color(r.nextInt(127), r.nextInt(127), r.nextInt(127));
  Baustein(String pinhalt) {
    Inhalt = pinhalt;
  }
  public void drawBaustein(int pos) {
    position = pos;    
    fill(farbe);
    rect((pos*50)+scroll, 200, 60, 50);
  }
  public void texti() {
    if (overRect((pos*50)+scroll, 200, 60, 50)) {
      fill(255, 255, 255);
      rect((pos*50)+scroll, 175, 200, 25);

      textSize(15);
      textAlign(LEFT, TOP);
      fill(farbe);
      text(Inhalt, (pos*50)+scroll+5, 180);
    }
  }
}
class Button {
  float xp, xl, yp, yl;
  String text;
  int buttoncolor, textcolor;
  Button(float px, float py, float pw, float ph, int colt, String label, int textcol) {
    xp = px;
    xl = pw;
    yp = py;
    yl = ph;
    buttoncolor = colt;
    text = label;
    textcolor = textcol;
  }
  public void drawButton() {
    fill(buttoncolor);
    rect(xp, yp, xl, yl);
    textSize(15);
    textAlign(CENTER, CENTER);
    fill(textcolor);
    text(text, xp+xl/2, yp+yl/2);
  }
}
boolean overEnqueue, overDequeue, overEmpty, overFront, overLeft, overRight = false;

public boolean overRect(int x, int y, int width, int height) {
  if (mouseX >= x && mouseX <= x+width && mouseY >= y && mouseY <= y+height) {
    return true;
  } else {
    return false;
  }
}

public void checkMouse() {
  if (overRect(20, 20, 120, 60)) overEnqueue = true;
  else overEnqueue = false;
  if (overRect(140, 20, 100, 60)) overDequeue = true;
  else overDequeue = false;
  if (overRect(20, 80, 380, 40)) overText = true;
  else overText = false;
  if (overRect(240, 20, 80, 30)) overEmpty = true;
  else overEmpty = false;
  if (overRect(320, 20, 80, 30)) overFront = true;
  else overFront = false;
  if (overRect(240, 50, 80, 30)) overLeft = true;
  else overLeft = false;
  if (overRect(320, 50, 80, 30)) overRight = true;
  else overRight = false;
}

public void mousePressed() {
  if (overEnqueue) enqueue(inputText);
  if (overDequeue) dequeue();
  if (overEmpty) isEmpty();
  if (overFront) front();
  if (overLeft) scrollLeft();
  if (overRight) scrollRight();
}
String inputText = "";
boolean overText = false;
public void keyPressed() {
  if (overText) {

    if (key == ENTER) {
      enqueue(inputText);
    } else if (key == BACKSPACE &&inputText.length()>0) {
      inputText = inputText.substring(0, inputText.length()-1);
    } else if (key == DELETE &&inputText.length()!=0) {
      inputText = "";
    } else
      inputText = inputText + key;
  }
}


public void drawInputField() {
  if (overText) fill(100, 149, 237);
  else fill(255, 255, 255);
  rect(20, 80, 380, 40);
  fill(0, 0, 0);
  textSize(18);
  textAlign(LEFT, TOP);
  text(inputText, 22, 100);
}

public void clearText() {
  inputText = "";
}
  public void settings() {  size(800, 400); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "queuetool" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
