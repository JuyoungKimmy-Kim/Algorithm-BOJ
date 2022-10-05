package bfs.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ17143 {

	static final int dy[] = { 0, -1, 1, 0, 0 };
	static final int dx[] = { 0, 0, 0, 1, -1 };

	static int R, C, M, hx, ret;
	static List<Integer> map[][];
	static Shark[] shark;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new ArrayList[R + 1][C + 1];
		for (int i = 0; i <= R; i++)
			for (int j = 0; j <= C; j++)
				map[i][j] = new ArrayList<>();

		shark = new Shark[M];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());

			shark[i] = new Shark(r, c, s, d, z);
			map[r][c].add(i);
		}

		for (int i = 0; i < C; i++) {
			fishingShark();

			if (i == C - 1)
				break;
			moveShark();
			removeShark();
			
			//print();
		}

		System.out.println(ret);
	}

	static void fishingShark() {
		hx++;
		for (int i = 1; i <= R; i++) {
			if (map[i][hx].size() != 0) {
				int idx = map[i][hx].get(0);
				map[i][hx].clear();
				shark[idx].alive = false;
				ret += shark[idx].z;
				break;
			}
		}
	}

	static void moveShark() {
		for (int i = 1; i <= R; i++)
			for (int j = 1; j <= C; j++)
				map[i][j].clear();

		for (int i = 0; i < M; i++) {
			if (shark[i].alive == false)
				continue;

			int y = shark[i].r;
			int x = shark[i].c;
			int d = shark[i].d;
			int s = shark[i].s;

			// 스피드를 줄여줌
			if (d == 1 || d == 2)
				s %= 2 * R - 2;
			else if (d == 3 || d == 4)
				s %= 2 * C - 2;

			int ny = y;
			int nx = x;
			for (int j = 1; j <= s; j++) {
				ny = ny + dy[d];
				nx = nx + dx[d];

				if (ny <= 0 || nx <= 0 || ny > R || nx > C) {
					d = changeDir(d);

					// 뒤 돌아서 다시 두 칸
					ny = ny + dy[d] * 2;
					nx = nx + dx[d] * 2;
				}
			}

			map[ny][nx].add(i);
			shark[i].r = ny;
			shark[i].c = nx;
			shark[i].d = d;
		}
	}

	static void removeShark() {
		for (int i = 1; i <= R; i++) {
			for (int j = 1; j <= C; j++) {
				if (map[i][j].size() > 1) {

					Collections.sort(map[i][j], (m1, m2) -> shark[m2].z-shark[m1].z);

					for (int k = 1; k < map[i][j].size(); k++) {
						int n = map[i][j].get(k);
						shark[n].alive = false;
					}

					int idx = map[i][j].get(0);
					map[i][j].clear();
					map[i][j].add(idx);
				}
			}
		}
	}

	static int changeDir(int d) {
		switch (d) {
		case 1:
			return 2;
		case 2:
			return 1;
		case 3:
			return 4;
		case 4:
			return 3;
		}

		return 0;
	}

	static void print() {

		System.out.println("======================");
		for (int i = 1; i <= R; i++) {
			for (int j = 1; j <= C; j++) {
				System.out.printf("%d ", map[i][j].size());
			}
			System.out.println();
		}

		System.out.println("Shark info");
		for (int i = 0; i < M; i++) {
			System.out.println( i+" ("+ shark[i].r + shark[i].c + ")"+ shark[i].d);
		}
	}

	static class Shark  {
		boolean alive;
		int r, c, s, d, z;

		Shark(int r, int c, int s, int d, int z) {
			this.r = r;
			this.c = c;
			this.s = s;
			this.d = d;
			this.z = z;
			this.alive = true;
		}

	}
}
