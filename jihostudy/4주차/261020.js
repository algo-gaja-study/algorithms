def print_2d_array(array):
  for arr in array:
    print(arr)

def solution(rows, columns, queries):
  answers = []
  
  board = []
  for i in range(rows):
    add = []
    for j in range(columns):
      add.append(i * columns +j+1)
    board.append(add)
      
  # print_2d_array(board)
  
  for qurie in queries:
    numbers = []
    
    srow  = qurie[0]-1
    scol  = qurie[1]-1
    erow  = qurie[2]-1
    ecol  = qurie[3]-1
    
    drow = [0,1,0,-1]
    dcol = [1,0,-1,0]
    
    #1. 순서대로 들어갈 숫자표 만들기
    dir = 0

    crow, ccol = srow, scol
    
    # print(f"Testing : {crow} {ccol} => {erow} {ecol}")
    numbers.append(board[srow][scol])
    crow += drow[dir]
    ccol += dcol[dir]
    
    # print(f"시작 : {crow} {ccol}")
    while(crow != srow or ccol != scol):
      numbers.append(board[crow][ccol])
      
      testRow = crow + drow[dir]
      testCol = ccol + dcol[dir]
      
      if(srow <= testRow <= erow and scol <= testCol <= ecol):
        crow += drow[dir]
        ccol += dcol[dir]
      else:
        dir = (dir + 1) % 4
        crow += drow[dir]
        ccol += dcol[dir]
    
    # print(numbers)
        
    #2. 회전시키기
    last = numbers[-1]
    numbers = numbers[0:-1]
    numbers.insert(0,last)
    # print(numbers)
  
    #3. 각 숫자를 자리에 넣기
    dir = 0

    crow, ccol = srow, scol
    
    # numbers.append(board[srow][scol])
    index = 0
    
    board[crow][ccol] = numbers[0]
    index += 1
    
    crow += drow[dir]
    ccol += dcol[dir]
    
    while(crow != srow or ccol != scol):
      # numbers.append(board[crow][ccol])
      board[crow][ccol] = numbers[index]
      index += 1
      
      testRow = crow + drow[dir]
      testCol = ccol + dcol[dir]
      
      if(srow <= testRow <= erow and scol <= testCol <= ecol):
        crow += drow[dir]
        ccol += dcol[dir]
      else:
        dir = (dir + 1) % 4
        crow += drow[dir]
        ccol += dcol[dir]
    
    answers.append(min(numbers))
  return answers
