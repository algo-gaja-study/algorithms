from collections import deque

def print_2d_arr(array):
  for arr in array:
    print(arr)

# maps[row][col] 에서부터 이어지는 최대 숫자들의 값
def BFS(maps,visited,row,col):
  N, M = len(maps), len(maps[0])
  drow = [0,1,0,-1]
  dcol = [1,0,-1,0]
  
  queue = deque()
  
  queue.append([row,col])
  visited[row][col] = True
  
  count = 0 
  while(len(queue) != 0):
    crow,ccol = queue.popleft()
    count += int(maps[crow][ccol])
    
    # 순회
    for i in range(4):
      nrow = crow + drow[i]
      ncol = ccol + dcol[i]
      
      if(0 <= nrow < N and 0 <= ncol < M and visited[nrow][ncol] == False and maps[nrow][ncol] != 'X'):
        queue.append([nrow,ncol])
        visited[nrow][ncol] = True
  
  return count
    

def solution(maps):
  N, M = len(maps), len(maps[0])
  
  visited = [[False]* M for _ in range(N)]
  answers = []
  
  for row in range(N):
    for col in range(M):
      if visited[row][col] == False and maps[row][col] != 'X':
        answers.append(BFS(maps,visited,row,col))
  
  if(len(answers) == 0):
    answers.append(-1)
    
  answers.sort()
  return answers


print(solution(["X591X","X1X5X","X231X", "1XXX1"]))
print(solution(["XXX","XXX","XXX"]))

