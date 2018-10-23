boolean overEnqueue, overDequeue, overEmpty, overFront, overLeft, overRight = false;

boolean overRect(int x, int y, int width, int height) {
  if (mouseX >= x && mouseX <= x+width && mouseY >= y && mouseY <= y+height) {
    return true;
  } else {
    return false;
  }
}

void checkMouse() {
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

void mousePressed() {
  if (overEnqueue) enqueue(inputText);
  if (overDequeue) dequeue();
  if (overEmpty) isEmpty();
  if (overFront) front();
  if (overLeft) scrollLeft();
  if (overRight) scrollRight();
}