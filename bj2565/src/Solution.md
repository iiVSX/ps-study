<pre>
Algorithm : LIS & DP
Problem : 전기줄이 주어졌을 때, 교차하는 전기줄 없게 만들기 위해 제거해야 하는 최소의 전기줄 개수
Idea : 
  초기 아이디어: LIS 알고리즘을 사용하여, 전기줄이 (src, dst)로 이루어질 때, src를 기준으로 정렬한 뒤 순회하며 dst를 기준으로 분할 정복하여 증가하는 부분수열의 최대의 개수를 구하면 겹치지 않는 전기줄의 최대 개수를 구할 수 있다.
</pre>
