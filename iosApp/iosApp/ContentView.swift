import SwiftUI
import Shared

struct ContentView: View {
	var body: some View {
		ComposeView()
			.ignoresSafeArea(.keyboard) // Compose handles its own safe area and keyboard
	}
}
