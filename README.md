[![Maintainability](https://api.codeclimate.com/v1/badges/09a82a26f83b115a8331/maintainability)](https://codeclimate.com/github/k2works/library/maintainability)
[![Test Coverage](https://api.codeclimate.com/v1/badges/09a82a26f83b115a8331/test_coverage)](https://codeclimate.com/github/k2works/library/test_coverage)

# 図書館の司書業務を支援するソフトウェア

## 司書業務とアプリケーションの対象範囲

このアプリケーションは、公立図書館の司書業務をサポートするソフトウェアです。

### 主な対象業務

* 貸出と返却　◎
* 書架の整理　△
* 選書と受入 〇

### このアプリケーションでは対象としない司書の業務

司書の役割や関心事を理解するための参考情報です。
アプリケーションの仕様を検討する時に、司書の業務の全体を理解することが役に立ちます。

* レファレンスサービス（相談）　△
* リファレンスサービス（記録）　-
* イベント（企画・運営・評価) -

### 図書館業務の課題


## サンプルアプリケーションの使い方

### 利用方法

* RDRA 2.0 ハンドブックを入手
* このリポジトリーをクローン
* Gradleタスク bootRunを実行(アプリケーションの起動)
* Gradleタスク jigReportを実行(設計ドキュメントの出力)

この実装例の内容を学ぶためのチュートリアルが公開されました。  
[図書館サンプルのチュートリアル](https://github.com/jnuank/library/tree/master/tutorial)

#### アプリケーションの内容
RDRA 2.0 ハンドブックのサンプル「図書館システム」の以下の業務を実装しています。

![business-usecase](https://user-images.githubusercontent.com/3654676/83082211-b4c08380-a0bd-11ea-8c31-a2b413c60c32.png)

アプリケーションは、```http://localhost:8080```でトップ画面を表示できます。

### RDRA 2.0 ハンドブックの図書館システムの実装例

モデルベースの要件定義手法 RDRA2.0 ハンドブックのサンプル「図書館システム」の実装です。
* [RDRA 2.0 ハンドブック](https://www.amazon.co.jp/RDRA2-0-%E3%83%8F%E3%83%B3%E3%83%89%E3%83%96%E3%83%83%E3%82%AF-%E8%BB%BD%E3%81%8F%E6%9F%94%E8%BB%9F%E3%81%A7%E7%B2%BE%E5%BA%A6%E3%81%AE%E9%AB%98%E3%81%84%E8%A6%81%E4%BB%B6%E5%AE%9A%E7%BE%A9%E3%81%AE%E3%83%A2%E3%83%87%E3%83%AA%E3%83%B3%E3%82%B0%E6%89%8B%E6%B3%95-%E7%A5%9E%E5%B4%8E%E5%96%84%E5%8F%B8-ebook/dp/B07STQZFBX/ref=sr_1_1?__mk_ja_JP=%E3%82%AB%E3%82%BF%E3%82%AB%E3%83%8A&keywords=rdra2.0&qid=1585531997&sr=8-1) (Kindle Unlimited会員は無償です)


#### 設計ドキュメント
JIGを使ってソースコードから設計ドキュメントを自動生成します。ドキュメントはbuild/jig に出力されます。  
JIGドキュメントの生成には、 [Graphviz](https://www.graphviz.org/) のインストールが必要です。

#### 実装の範囲
ビジネスルールを中心に実装しています。

RDRA 2.0で可視化した以下のビジネスルールと関連するユースケース、画面、テーブルを実装しています。

* 貸出制限ルール
* 予約の状態遷移

#### 貸出業務と貸出制限ルール

![composit-usecase-loan](https://user-images.githubusercontent.com/3654676/83082272-e6394f00-a0bd-11ea-97c1-7e2e4cc3299c.png)

![restriction](https://user-images.githubusercontent.com/3654676/83082253-da4d8d00-a0bd-11ea-9963-7f3259da6153.png)

#### 予約の状態遷移

![reservatoin-state](https://user-images.githubusercontent.com/3654676/83082235-cbff7100-a0bd-11ea-8ecb-25cf8eef6f9b.png)

以下の業務は未実装です。

* 蔵書管理（資料の注文と蔵書として登録する）
* 会員管理（会員の登録）

## 要件定義・仕様化・実装の継ぎ目をなくす開発手法

この図書館サンプルは、以下の考え方とやり方で実装しています。

* 三層構造 + ビジネスロジック
* 仕様の記述にプログラミング言語(Java,SQL,HTML)を使う
* 仕様の可視化にツール(JIG)を使う

### 三層構造＋ビジネスロジック

![layers](https://user-images.githubusercontent.com/3654676/82853460-ce888c00-9f40-11ea-8aa9-39bf8d505914.jpg)
ビジネスロジックを独立させるドメイン駆動設計のアプローチを採用しています。

### ドメインオブジェクトを中心にしたアプリケーション構造

![mapping](https://user-images.githubusercontent.com/3654676/82853466-d0524f80-9f40-11ea-9d58-9c781c062b0f.jpg)
RDRAの要件定義モデルと実装を、以下のように対応させています。

Spring MVCとMyBatisを使い、ドメインオブジェクトを中心に周辺の外部形式との双方向のマッピングを実現するアプリケーション構造を採用しています。

#### ドメインオブジェクトのモデル（ビジネスロジックの表現）

![model](https://user-images.githubusercontent.com/3654676/83083740-e3405d80-a0c1-11ea-9765-4b46efccee40.png)

ソースコードから、JIGで自動生成したドメインモデルのパッケージ構成図です。  
このモデルを中心に図書館アプリケーションを組み立てています。

### CCSR: Continuous Concurrent Stepwise Refinment

開発手法として、要件定義・仕様化・実装の継ぎ目をなくすCCSR手法を採用しています。

この図書館サンプル実装は、CCSR手法の実践例として開発しています。

![ccsr](https://user-images.githubusercontent.com/3654676/82853451-cb8d9b80-9f40-11ea-8c8d-ec8a74f2194f.jpg)

参考記事： [要件定義・仕様化・実装の継ぎ目をなくすCCSR開発手法](https://masuda220.hatenablog.com/entry/2020/05/27/103750)

### RDRAアドイン 

RDRA手法のモデリングツールとして、Enterprise ArchitectのRDRAアドインを使っています。

* [Enterprise ArchitectでRDRA 2.0を利用する](https://www.sparxsystems.jp/products/EA/tech/RDRA.htm)

### JIG (設計可視化ツール)

Javaで記述した内容を、俯瞰したり、一覧するためのツールです。

プログラミング言語で仕様を記述するCCSR手法を実践するために必須のツールです。

* [JIGリポジトリ](https://github.com/dddjava/jig)

### RDRA - CCSR - JIG 関係図

![RDRA-CCSR-JIG](https://user-images.githubusercontent.com/3654676/82853471-d3e5d680-9f40-11ea-8bdb-e6de0bd28672.jpg)

RDRAのモデル要素（左）、三層＋ビジネスロジックの構造（中央）、JIGの出力ドキュメント（右）の対応関係です。

#### ビジネスルール駆動

図の青背景でしめした、RDRAで可視化したビジネスルールをドメインモデルとして実装することが、このアプリケーションの中核です。

RDRA 2.0で可視化された内容と、実装された内容の対応は、以下のJIGドキュメントで確認できます。

* ユースケース複合図
* パッケージ関係図（depth4, depth5）
* 区分図と区分使用図
* メソッド呼び出し関係図

## 参考情報

* 書籍：[現場で役立つシステムの原則 ～変更を楽で安全にするオブジェクト指向の実装技法](https://gihyo.jp/book/2017/978-4-7741-9087-7)
* ドメインオブジェクトの設計パターン：[設計ガイドライン](https://github.com/masuda220/business-logic-patterns/wiki/%E8%A8%AD%E8%A8%88%E3%82%AC%E3%82%A4%E3%83%89%E3%83%A9%E3%82%A4%E3%83%B3)
* オブジェクトと外部形式のマッピング技法の説明：[CCSRオブジェクトマッピング](https://github.com/system-sekkei/ccsr-object-mapping)