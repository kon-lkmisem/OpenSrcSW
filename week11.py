import sys
class Calc:
     def sum(self, a,b):
          result = a+b
          print("%s + %s = %s 입니다." % (a,b,result))
     def sub(self, a,b):
          result = a-b
          print("%s - %s = %s 입니다." % (a,b,result))
     def multi(self, a,b):
          result = a/b
          print("%s / %s = %s 입니다." % (a,b,result))
     def divi(self, a,b):
          result = a*b
          print("%s * %s = %s 입니다." % (a,b,result))

class Stack:
    # 빈 스택을 배열로 초기화
    def __init__(self):
        self.data = [] 
        
    # 스택의 크기
    def size(self):
        return len(self.data)
    
    # 빈 스택 판단 여부
    def isEmpty(self):
        return self.size() == 0
    
    # 데이터 원소 추가
    def push(self, item):
         self.data.append(item)
        
    # 데이터 원소 삭제 (리턴)
    def pop(self):
         return self.data.pop()
    
    # 스택의 꼭대기 원소 반환
    def peek(self):
        return self.data[-1]

def infixTopostfix(tokenList): #중위 표현식을 후위 표현식으로 변환
    prec = {
        '*': 3,
        '/': 3,
        '+': 2,
        '-': 2,
        '(': 1,
    }

    opStack = Stack()
    postfixList = []

    for token in tokenList:
        if token.isdigit():
            postfixList.append(float(token))         
        elif token == ')':
            if token == ')':
                while opStack.peek() != '(':
                #print(opStack.peek())
                    postfixList.append(opStack.pop())
                opStack.pop()
        else:
            if opStack.isEmpty() == False:  
                if prec[opStack.peek()] >= prec[token] and token != '(':
                    postfixList.append(opStack.pop())
                    opStack.push(token)
                elif prec[opStack.peek()] >= prec[token] and token == '(':
                    opStack.push(token)
                else:
                    opStack.push(token)
            elif opStack.isEmpty() == True:
                opStack.push(token)

    while not opStack.isEmpty():
        postfixList.append(opStack.pop())

    return postfixList

def postfixEval(tokenList): #후위 표현식 계산
    opStack = Stack()
    for token in tokenList:
        if type(token) is float:
            opStack.push(token)
        elif token == '*':
            tmp1 = opStack.pop()
            tmp2 = opStack.pop()
            opStack.push(tmp2*tmp1)
        elif token == '/':
            tmp1 = opStack.pop()
            tmp2 = opStack.pop()
            opStack.push(tmp2/tmp1)
        elif token == '+':
            tmp1 = opStack.pop()
            tmp2 = opStack.pop()
            opStack.push(tmp2+tmp1)
        elif token == '-':
            tmp1 = opStack.pop()
            tmp2 = opStack.pop()
            opStack.push(tmp2-tmp1)
    return opStack.pop()

def copy(file1,file2):
     print("#실습 6")
     f1 = open(str(file1), mode='rt', encoding='utf-8')
     f2 = open(str(file2), mode='w', encoding='utf-8')
     while 1:
          line = f1.readline()
          if not line:
               break
          else:
               f2.write(str(line))
               
     print("파일 "+file1+"로 부터 파일 " +file2+"로 복사를 완료하였습니다.")
     f1.close()
     f2.close()

def wordCount(file):
     print("#실습 7")
     print("파일명:",file)
     l=0
     w=0
     f = open(str(file), mode='rt', encoding='utf-8')
     
     while 1:
          line = f.readline()
          if not line:
               break
          else:
               l+=1
               w+=len(line.split(" "))
     f.close()
     print("line: ",l)
     print("word: ",w)

select = 0s
cp = ["",""]
for arg in sys.argv:
     if "-" in arg:
          if arg == "-cp":
               select=1
          elif arg == "-wc":
               select=2
          else:
               select=0
     else:
          if select==1:
               if cp[0]=="":
                    cp[0]=arg
               elif cp[1] =="":
                    cp[1]=arg
                    copy(cp[0],cp[1])
                    cp = ["",""]
               print()
          elif select==2:
               wordCount(arg)
               print()

print("#실습 8")       
calc=Calc()
calc.sum(1,2)
calc.sub(5,1)
calc.multi(2,3)
calc.divi(6,2)
print()

print("#실습 9")

a=input("계산식 입력 : ")

c=a.split(" ")
print("계산식 출력:"+a.replace(" ","")+"="+str(postfixEval(infixTopostfix(c))))



