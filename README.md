# pressure-check-app

## 概要
* 日本4地点の現在の気圧が確認できるアプリ
* 地点の変更をすると、地点名、気圧情報、背景画像が差し替わるように設定（DataBinding+LiveData）
* アーキテクチャはMVVM
* データ取得には[Dark Sky API](https://darksky.net/dev/docs)を使用

## デモ
![pressure-check-app-2](https://user-images.githubusercontent.com/12453846/63096325-a4b13a80-bfa8-11e9-9fc2-b6874ef48d5f.gif)

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
