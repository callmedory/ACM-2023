import tkinter as tk
from tkinter import ttk
from math import *


def is_float(input_str):
    try:
        float(input_str)
        return True
    except ValueError:
        return False


def perform_task1():
    if fieldA.get() == "" or fieldB.get() == "" or fieldC.get() == "" or fieldD.get() == "" \
            or not is_float(fieldA.get()) or not is_float(fieldB.get()) or not is_float(fieldC.get()) or not is_float(fieldD.get()):
        field1.delete(0, tk.END)
        field1.insert(0, "Помилка при введенні даних.")
    else:
        a = float(fieldA.get())
        b = float(fieldB.get())
        c = float(fieldC.get())
        d = float(fieldD.get())
        if d != 0:
            result = pow((a / d), 2) + pow((b / d), 3) + pow((c / 2), 4)
            field1.delete(0, tk.END)
            field1.insert(0, result)
        else:
            field1.delete(0, tk.END)
            field1.insert(0, "Помилка при введенні даних.")


def perform_task2():
    if fieldF.get() == "" or fieldL.get() == "" or fieldK.get() == "" or fieldW.get() == "" or fieldD2.get() == "" \
            or not is_float(fieldF.get()) or not is_float(fieldL.get()) or not is_float(fieldK.get()) or not is_float(fieldW.get()) or not is_float(fieldD2.get()):
        field2.delete(0, tk.END)
        field2.insert(0, "Помилка при введенні даних.")
    else:
        f = float(fieldF.get())
        l = float(fieldL.get())
        k = float(fieldK.get())
        w = float(fieldW.get())
        d2 = float(fieldD2.get())
        if l * k > 0:
            if f == 0:
                result = log10(l * k) + d2 * sin(w * k)
            else:
                result = cos(w * k) + log10(l * k)
            field2.delete(0, tk.END)
            field2.insert(0, result)
        else:
            field2.delete(0, tk.END)
            field2.insert(0, "Помилка при введенні даних.")


def perform_task3():
    if fieldB3.get() == "" or not is_float(fieldB3.get()):
        for i in range(len(fieldRes)):
            fieldRes[i].delete(0, tk.END)
            fieldRes[i].insert(0, "Помилка при введенні даних.")
    else:
        b3 = float(fieldB3.get())
        for a3 in range(-4, 19):
            result_third_task = (a3 ** 2 + b3 ** 2) ** 0.5 - (a3 + b3) ** 2
            fieldRes[a3 + 4].delete(0, tk.END)
            fieldRes[a3 + 4].insert(0, str(result_third_task))


root = tk.Tk()
root.title("Лабораторна робота №1")
root.geometry("670x600")

notebook = ttk.Notebook(root)

# Task 1
tab1 = ttk.Frame(notebook)

labelA = ttk.Label(tab1, text="Введіть значення A")
labelA.pack()
fieldA = ttk.Entry(tab1)
fieldA.pack()

labelB = ttk.Label(tab1, text="Введіть значення B")
labelB.pack()
fieldB = ttk.Entry(tab1)
fieldB.pack()

labelC = ttk.Label(tab1, text="Введіть значення C")
labelC.pack()
fieldC = ttk.Entry(tab1)
fieldC.pack()

labelD = ttk.Label(tab1, text="Введіть значення D")
labelD.pack()
fieldD = ttk.Entry(tab1)
fieldD.pack()

label1C = ttk.Label(tab1)
label1C.pack()

task1 = ttk.Button(tab1, text="Виконати завдання", command=perform_task1)
task1.pack()

label1 = ttk.Label(tab1, text="Результат")
label1.pack()
field1 = ttk.Entry(tab1)
field1.pack()
tab1.pack()

# Task 2
tab2 = ttk.Frame(notebook)

labelF = ttk.Label(tab2, text="Введіть значення F")
labelF.pack()
fieldF = ttk.Entry(tab2)
fieldF.pack()

labelL = ttk.Label(tab2, text="Введіть значення L")
labelL.pack()
fieldL = ttk.Entry(tab2)
fieldL.pack()

labelK = ttk.Label(tab2, text="Введіть значення K")
labelK.pack()
fieldK = ttk.Entry(tab2)
fieldK.pack()

labelW = ttk.Label(tab2, text="Введіть значення W")
labelW.pack()
fieldW = ttk.Entry(tab2)
fieldW.pack()

labelD2 = ttk.Label(tab2, text="Введіть значення D")
labelD2.pack()
fieldD2 = ttk.Entry(tab2)
fieldD2.pack()

label2C = ttk.Label(tab2)
label2C.pack()

task2 = ttk.Button(tab2, text="Виконати завдання", command=perform_task2)
task2.pack()

label2 = ttk.Label(tab2, text="Результат")
label2.pack()
field2 = ttk.Entry(tab2)
field2.pack()
tab2.pack()

# Task 3
tab3 = ttk.Frame(notebook)

labelB3 = ttk.Label(tab3, text="Введіть значення B")
labelB3.grid(row=0, column=0, sticky="w")
fieldB3 = ttk.Entry(tab3)
fieldB3.grid(row=0, column=1)

task3 = ttk.Button(tab3, text="Виконати завдання", command=perform_task3)
task3.grid(row=1, column=0, columnspan=2)

labelRes = []
fieldRes = []
for i in range(23):
    label_res = ttk.Label(tab3, text=f"f[{i-4}] = ")
    label_res.grid(row=i+2, column=0, sticky="e")
    field_res = ttk.Entry(tab3)
    field_res.grid(row=i+2, column=1)
    labelRes.append(label_res)
    fieldRes.append(field_res)
tab3.pack() 

notebook.add(tab1, text="Завдання №1")
notebook.add(tab2, text="Завдання №2")
notebook.add(tab3, text="Завдання №3")

notebook.pack()

root.mainloop()

