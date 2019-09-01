# pressure-check-app

## 概要
* 日本4地点の現在の気圧が確認できるアプリ
* 地点の変更をすると、地点名、気圧情報、背景画像が差し替わるように設定（DataBinding+LiveData）
* アーキテクチャはMVVM
* データ取得には[Dark Sky API](https://darksky.net/dev/docs)を使用

## デモ
![Sep-01-2019 16-05-43](https://user-images.githubusercontent.com/12453846/64072965-82baf600-ccd2-11e9-9e22-6d5f81adec49.gif)


## 環境
* macOS: 10.15 Beta
* Android Studio: 3.4

## 使用しているライブラリ
### サポートライブラリ
* AndroidX

### API通信
* okhttp3
* retrofit2
* moshi

### DI
* Koin

### UI
* coroutines
