public class Solution {
     private int MinimumSum2(int[][] grid, int u, int d, int l, int r)
  {
      int min_i = grid.Length, max_i = 0;
      int min_j = grid[0].Length, max_j = 0;
      for (int i = u; i <= d; i++)
      {
          for (int j = l; j <= r; j++)
          {
              if (grid[i][j] == 1)
              {
                  min_i = Math.Min(min_i, i);
                  min_j = Math.Min(min_j, j);
                  max_i = Math.Max(max_i, i);
                  max_j = Math.Max(max_j, j);
              }
          }
      }
      return min_i <= max_i ? (max_i - min_i + 1) * (max_j - min_j + 1)
                            : int.MaxValue / 3;
  }

  private int[][] Rotate(int[][] vec)
  {
      int n = vec.Length, m = vec[0].Length;
      int[][] ret = new int[m][];
      for (int i = 0; i < m; i++)
      {
          ret[i] = new int[n];
      }
      for (int i = 0; i < n; i++)
      {
          for (int j = 0; j < m; j++)
          {
              ret[m - j - 1][i] = vec[i][j];
          }
      }
      return ret;
  }

  private int Solve(int[][] grid)
  {
      int n = grid.Length, m = grid[0].Length;
      int res = n * m;
      for (int i = 0; i + 1 < n; i++)
      {
          for (int j = 0; j + 1 < m; j++)
          {
              res = Math.Min(
                  res, MinimumSum2(grid, 0, i, 0, m - 1) +
                           MinimumSum2(grid, i + 1, n - 1, 0, j) +
                           MinimumSum2(grid, i + 1, n - 1, j + 1, m - 1));
              res = Math.Min(res,
                             MinimumSum2(grid, 0, i, 0, j) +
                                 MinimumSum2(grid, 0, i, j + 1, m - 1) +
                                 MinimumSum2(grid, i + 1, n - 1, 0, m - 1));
          }
      }
      for (int i = 0; i + 2 < n; i++)
      {
          for (int j = i + 1; j + 1 < n; j++)
          {
              res = Math.Min(res,
                             MinimumSum2(grid, 0, i, 0, m - 1) +
                                 MinimumSum2(grid, i + 1, j, 0, m - 1) +
                                 MinimumSum2(grid, j + 1, n - 1, 0, m - 1));
          }
      }
      return res;
  }

  public int MinimumSum(int[][] grid)
  {
      int[][] rgrid = Rotate(grid);
      return Math.Min(Solve(grid), Solve(rgrid));
  }
}