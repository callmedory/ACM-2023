import sys
import matplotlib.pyplot as plt
import numpy as np
from PyQt5.QtGui import *
from PyQt5.QtWidgets import *
from algorythm import function, bisection, find_ranges


class Example(QWidget):
    def __init__(self):

        super().__init__()

        self.initUI()

    def initUI(self):

        grid = QGridLayout()
        self.setLayout(grid)
        self.move(300, 150)
        self.setWindowTitle('Лабораторна робота №4')

        self.font = QFont('Arial', 12)

        self.empty = QLabel('')
        grid.addWidget(self.empty, 0, 0, 0, 8)

        self.a = QLineEdit('0')
        self.b = QLineEdit('1')
        self.a.setMaximumSize(50, 50)
        self.b.setMaximumSize(50, 50)

        self.l1 = QLabel("Введіть діапазон:")
        self.lable_from = QLabel('Від')
        self.lable_to = QLabel('до')

        self.tlable = QLabel('Точність:')
        self.t = QLineEdit('0.001')
        self.t.setMaximumSize(50, 50)

        self.xlable = QLabel('X0 = ?')
        self.ylable = QLabel('F(X0) = ?')

        self.find_ranges_but = QPushButton('Знайти діапазони, на яких є корені')
        self.find_ranges_but.clicked.connect(self.show_ranges)
        self.lranges = QLabel('')

        button_show_ideal = QPushButton("Побудувати графік")
        button_show_ideal.clicked.connect(self.showGraph)

        grid.addWidget(self.l1, 2, 0, 1, 10)
        grid.addWidget(self.lable_from, 3, 0)
        grid.addWidget(self.a, 3, 1)
        grid.addWidget(self.lable_to, 3, 2)
        grid.addWidget(self.b, 3, 3)
        grid.addWidget(self.tlable, 4, 0, 1, 3)
        grid.addWidget(self.t, 4, 3)

        grid.addWidget(self.xlable, 5, 0, 1, 3)
        grid.addWidget(self.ylable, 6, 0, 1, 3)

        grid.addWidget(self.find_ranges_but, 7, 0, 1, 10)
        grid.addWidget(self.lranges, 8, 0, 1, 10)

        grid.addWidget(button_show_ideal, 9, 0, 1, 10)
        label2 = QLabel(self)
        label2.setText("Введіть довжину відрізку [a,b] та точність:")
        grid.addWidget(label2, 1, 0, 1, 10)
        self.show()

    def show_ranges(self):
        ranges = find_ranges()
        s = 'Рівняння має корені в наступних діапазонах:\n' \
        '{0} та {1}'.format(ranges[0], ranges[1])
        self.lranges.setText(s)

    def getDots(self):
        a = float(self.a.text())
        b = float(self.b.text())
        e = float(self.t.text())

        self.x = np.linspace(0, 3, 100)
        self.y = list(map(function, self.x))

        return a, b, e

    def showGraph(self):
        a, b, e = self.getDots()

        if function(a) * function(b) > 0:
            error = QMessageBox()
            error.setText('Invalid range')
            error.exec()
        else:
            x0, y0 = bisection(a, b, e)

            self.xlable.setText('X0 = {}'.format(x0))
            self.ylable.setText('F(X0) = {}'.format(y0))

            plt.plot(self.x, self.y)
            plt.plot(x0, y0, 'o')

            plt.grid(True)
            plt.show()


if __name__ == '__main__':
    app = QApplication(sys.argv)
    ex = Example()
    sys.exit(app.exec_())

algorythm.py
import matplotlib.pyplot as plt
import numpy as np
import math


def df(x):
    return 2.2 - math.log(2) * math.pow(2, x)


def function(x):
    return 2.2*x - math.pow(2, x)


def find_ranges():
    a = -100
    b = 100
    step = 1
    n = (b-a) / step
    c = a
    b = a + step
    kranges = []
    for i in range(int(n)):

        fa = function(a)
        fb = function(b)
        if fa*fb < 0:
            kranges.append([a, b])
        a = b
        b = b + step
    return kranges


def bisection(a, b, e):
    n = 0
    while not abs(b - a) <= e:
        c = (a + b) / 2
        if function(c) == 0:
            return c, function(c)
        f1 = function(a)
        f2 = function(c)
        if f1*f2 <= 0:
            b = c
        else:
            a = c
        n += 1
    c = (a + b) / 2
    return c, function(c)


if __name__ == "__main__":
    a = 0
    b = 3
    e = 0.001
    x0, y0 = bisection(a, b, e)

    print(find_ranges())

    x = np.linspace(0, 3, 100)
    y = np.array([function(i) for i in x])

    dy = df(x)

    plt.plot(x, y, [x0], [y0], 'o', [a], [0], '|', [b], [0], '|', x, dy)
    plt.grid(True)
    plt.show()
