# hibari
楕円曲線を用いた並列素因数分解アルゴリズムの実装．

## 技術的要件
* Java Development Kit 8

## タスク
メソッドとして実装すべき処理(増えるかも):
* [x] :wrench: モジュラ逆元の計算
* [x] :wrench: 拡張 Euclid 互除法
* [x] :wrench: LCM, k-LCM の計算
* [x] :wrench: PowerSmooth な自然数の生成
* [x] :wrench: Eratosthenes ふるい法
* [x] :wrench: Miller-Rabin 素数判定法
* [x] :sparkles: 楕円曲線の生成
* [x] :sparkles: 楕円曲線上の k 倍演算
* [x] :sparkles: 全体処理の統括
* [ ] :sparkles: 計算結果の検証

## Git 運用方針
### ブランチ
* master: リリース
* develop: 開発
* test: テスト

### コミットメッセージ
* :tada: イニシャルコミット
* :sparkles: 新機能の実装
* :books: ドキュメント
* :hammer_and_wrench: 修正
* :wastebasket: 削除
