from collections import deque

def print_2d_array(array):
  for arr in array:
    print(arr)
    

def BFS(maps,start,end):
  row, col = len(maps), len(maps[0])
  
  drow = [0,1,0,-1]
  dcol = [1,0,-1,0]
  
  queue = deque()
  visited = [[False] * col for _ in range(row)]
  
  queue.append([start[0],start[1],0]) 
  visited[start[0]][start[1]] = True
  
  while(len(queue) != 0):
    crow, ccol, ccount = queue.popleft()
    
    # 종료조건
    if(crow == end[0] and ccol == end[1]):
      return ccount
    
    # 순회
    for i in range(4):
      nrow = crow + drow[i]
      ncol = ccol + dcol[i]
      
      if 0 <= nrow < row and 0 <= ncol < col and maps[nrow][ncol] != "X" and visited[nrow][ncol] == False:
        queue.append([nrow,ncol, ccount + 1])
        visited[nrow][ncol] = True

  return -1
  

def solution(maps):
  start = None
  end= None
  labber = None
  
  # 각 위치 찾기
  for r, map in enumerate(maps):
    for c, item in enumerate(map):
      if(item == "S"):
        start = [r,c]
      elif(item == "E"):
        end = [r,c] 
      elif(item == "L"):
        labber = [r,c] 
  
  count1 = BFS(maps,start,labber)
  if(count1 == -1):
    return -1
  count2 = BFS(maps,labber,end)
  if(count2 == -1):
    return -1
  return count1 + count2
