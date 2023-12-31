<pre>
Algorithm : 부분합
Problem : M이 주어졌을 때, N개의 수에서 M으로 나누어 떨어지는 부분합(i <= j, Ai+Ai+1....+Aj) 쌍을 구하여라.
Idea : 
  초기 아이디어: j>i일 때, j까지의 부분합에서 i까지의 부분합을 뺀 결과가 M으로 나누어진다면 (i+1, j)쌍이 존재한다.
              슬라이딩 윈도우를 이용하여 부분합 알고리즘을 사용한다. Sn+1 - Sn = A[n]. 그렇다면 O(N)에 처리가 가능하다.
              따라서, 0부터 i까지의 누적합을 Si라고할 때, Si = Si-1 + A[n]이고, mod[Si % M]을 기록한다.
              mod[Si%M]이 0인 경우에는 경우의 수를 1 늘려주고, 모든 순회가 종료되면 mod 배열을 0~M-1까지 순회하며 mC2를 더해준다면 모든 순서쌍을 구할 수 있다.
</pre>
