<pre>
Algorithm : LCS & DP
Problem : 두 문자열이 주어졌을 때, 최장 공통 문자열(LCS)를 구하여라
Idea : 
  초기 아이디어: 두 문자열을 s1, s2라고할 때, s1을 순회하며 s2에서 해당 문자가 처음 나타나는 곳을 dp[i] = j로 기록하고, i가 증가함에 따라 j가 커지면 s1, s2에서 둘다 인덱스가 증가하는 방향으로 문자열이 자랄테니 이 때의 최장 문자열의 길이를 구한다.
  문제점 : ABABCAB, BABCAB라는 예제를 생각해보자. s1이 C일 때, 이미 ABAB라는 최장문자열을 가지고 있으므로, dp[4] = 6으로 저장되어있기 때문에, C를 무시한다. 따라서 부정확한 결과가 나온다.
  해결점 : dynamic programming은 모든 경우에 대해 고려해주어야 한다. 따라서 점화식은 s1.length * s2.length 차원에서 dp[i][j] = if s1[i] == s2[j] then dp[i-1][j-1] + 1 else max(dp[i-1][j], dp[i][j-1])이 되어야 한다.
</pre>
